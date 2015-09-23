package com.anhnguyen.data.repository.datasource;

import com.anhnguyen.data.database.DatabaseApi;
import com.anhnguyen.data.database.DatabaseApiImpl;
import com.anhnguyen.data.net.RestApi;
import com.anhnguyen.data.net.RestApiImpl;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link SongDataStore}.
 */
@Singleton
public class SongDataStoreFactory {

    private final Context context;

    @Inject
    public SongDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
    }

    /**
     * Create {@link SongDataStore}
     */
    public SongDataStore create() {
        return createCloudDataStore();
    }

    /**
     * Create {@link SongDataStore} to retrieve data from the Cloud.
     */
    public SongDataStore createCloudDataStore() {
        RestApi restApi = new RestApiImpl(this.context);
        DatabaseApi databaseApi = new DatabaseApiImpl(this.context);

        return new CloudSongDataStore(restApi, databaseApi);
    }

    /**
     * Create {@link DatabaseSongDataStore} to retrieve data from the Cloud.
     */
    public DatabaseSongDataStore createDatabaseDataStore() {
        DatabaseApi databaseApi = new DatabaseApiImpl(context);
        return new DatabaseSongDataStore(databaseApi, context);
    }

}
