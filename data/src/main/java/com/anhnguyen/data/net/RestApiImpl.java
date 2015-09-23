package com.anhnguyen.data.net;

import com.anhnguyen.data.entity.SongEntity;
import com.anhnguyen.data.exception.NetworkConnectionException;
import com.anhnguyen.data.webservice.GetPopularSongsApi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.net.MalformedURLException;
import java.util.List;

import retrofit.RestAdapter;
import rx.Observable;
import rx.Subscriber;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RestApiImpl implements RestApi {

    private static final String TAG = RestApiImpl.class.getSimpleName();
    private final Context context;

    /**
     * Constructor of the class
     *
     * @param context {@link Context}.
     */
    public RestApiImpl(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
    }

    @Override
    public Observable<List<SongEntity>> getPopularSongs() {
        return Observable.create(new Observable.OnSubscribe<List<SongEntity>>() {
            @Override
            public void call(Subscriber<? super List<SongEntity>> subscriber) {
                if (isThereInternetConnection()) {
                    try {
                        List<SongEntity> storyEntities = getPopularSongsFromApi();
                        if (storyEntities != null) {
                            Log.d("getPopularSongs", "getPopularSongs " + storyEntities.size());
                            subscriber.onNext(storyEntities);
                            subscriber.onCompleted();
                        } else {
                            Log.d("getPopularSongs", "getPopularSongs failed");
                            subscriber.onError(new NetworkConnectionException("Cannot getFeeds through cloud-Api"));
                        }
                    } catch (Exception e) {
                        Log.d("getFeeds", "getFeeds error");
                        e.printStackTrace();
                        subscriber.onError(new NetworkConnectionException("getFeeds error " + e.getCause()));
                    }
                } else {
                    subscriber.onError(new NetworkConnectionException("Don't have internet access"));
                }
            }
        });
    }

    private List<SongEntity> getPopularSongsFromApi() throws MalformedURLException {
        final GetPopularSongsApi getPopularSongsApi =
            new RestAdapter.Builder()
                .setEndpoint(RestApi.API_BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String message) {
                        Log.d("Retrofit", message);
                    }
                }).build().create(GetPopularSongsApi.class);
        return null;//getPopularSongsApi.getPopularSongs();
    }


    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
            (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
