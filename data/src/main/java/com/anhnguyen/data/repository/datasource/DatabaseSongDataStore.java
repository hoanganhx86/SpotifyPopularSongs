package com.anhnguyen.data.repository.datasource;

import com.anhnguyen.data.database.DatabaseApi;
import com.anhnguyen.data.entity.SongEntity;

import android.content.Context;

import java.util.List;

import rx.Observable;

public class DatabaseSongDataStore implements SongDataStore {

    private Context context;
    private DatabaseApi databaseApi;


    public DatabaseSongDataStore(DatabaseApi databaseApi, Context context){
        this.context = context;
        this.databaseApi = databaseApi;
    }


    @Override
    public Observable<List<SongEntity>> getPopularSongs() {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }
}
