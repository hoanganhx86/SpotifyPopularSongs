package com.anhnguyen.data.database;

import com.anhnguyen.data.entity.SongEntity;

import android.content.Context;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class DatabaseApiImpl implements DatabaseApi {

    private static final String TAG = "UBLDatabaseApiImpl";

    private Context context;
    public DatabaseApiImpl(Context context){
        this.context = context;
    }


    @Override
    public Observable<List<SongEntity>> getPopularSongs() {
        return Observable.create(new Observable.OnSubscribe<List<SongEntity>>() {
            @Override
            public void call(Subscriber<? super List<SongEntity>> subscriber) {

            }
        });
    }

    @Override
    public void saveSongsToDb(SongEntity songEntity) {

    }
}
