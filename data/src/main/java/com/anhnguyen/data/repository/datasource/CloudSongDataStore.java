package com.anhnguyen.data.repository.datasource;

import com.anhnguyen.data.database.DatabaseApi;
import com.anhnguyen.data.entity.SongEntity;
import com.anhnguyen.data.net.RestApi;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * {@link SongDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudSongDataStore implements SongDataStore {

    private static final String TAG = "CloudSongDataStore";

    private final RestApi restApi;
    private final DatabaseApi databaseApi;

    private final Action1<SongEntity> saveSongToDbAction =
        songEntity -> {
            if (songEntity != null) {
                //Log.d(TAG, "saveSongToDbAction entity.name " + songEntity.getName());
                CloudSongDataStore.this.databaseApi.saveSongsToDb(songEntity);
            }
        };

    /**
     * Construct a {@link SongDataStore} based on connections to the api (Cloud).
     *
     * @param restApi   The {@link RestApi} implementation to use.
     * @param databaseApi A {@link DatabaseApi} to interact with DB.
     */
    public CloudSongDataStore(RestApi restApi, DatabaseApi databaseApi) {
        this.restApi = restApi;
        this.databaseApi = databaseApi;
    }

    @Override
    public Observable<List<SongEntity>> getPopularSongs() {
        return this.restApi.getPopularSongs();
    }

}
