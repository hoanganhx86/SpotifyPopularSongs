package com.anhnguyen.data.repository;

import com.anhnguyen.data.entity.mapper.SongEntityDataMapper;
import com.anhnguyen.data.repository.datasource.SongDataStore;
import com.anhnguyen.data.repository.datasource.SongDataStoreFactory;
import com.domain.Song;
import com.domain.repository.SongRepository;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link com.domain.repository.SongRepository} for retrieving song data.
 */
@Singleton
public class SongDataRepository implements SongRepository {

    private final SongDataStoreFactory songDataStoreFactory;

    /**
     * Constructs a {@link SongRepository}.
     *
     * @param dataStoreFactory     A factory to construct different data source implementations.
     */
    @Inject
    public SongDataRepository(SongDataStoreFactory dataStoreFactory) {
        this.songDataStoreFactory = dataStoreFactory;
    }

    @Override
    public Observable<List<Song>> getPopularSongs() {
        // get feeds from cloud
        Log.d("SongDataRepository", "SongDataRepository do getPopularSongs ");
        final SongDataStore songDataStore = this.songDataStoreFactory.createCloudDataStore();
        return songDataStore.getPopularSongs().map(entities -> SongEntityDataMapper.transformSongs(entities));
    }
}
