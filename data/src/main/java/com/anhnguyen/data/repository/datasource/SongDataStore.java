package com.anhnguyen.data.repository.datasource;

import com.anhnguyen.data.entity.SongEntity;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface SongDataStore {

    /**
     * get popular songs {@link Observable}
     */
    Observable<List<SongEntity>> getPopularSongs();
}
