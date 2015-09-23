package com.anhnguyen.data.database;

import com.anhnguyen.data.entity.SongEntity;

import java.util.List;

import rx.Observable;

public interface DatabaseApi {

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link SongEntity}.
     */
    Observable<List<SongEntity>> getPopularSongs();

    void saveSongsToDb(SongEntity songEntity);
}
