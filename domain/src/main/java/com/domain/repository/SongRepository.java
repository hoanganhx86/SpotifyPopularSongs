package com.domain.repository;

import com.domain.Song;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link Song} related data.
 */
public interface SongRepository {

    /**
     * Get an {@link Observable} list popular songs.
     */
    Observable<List<Song>> getPopularSongs();

}
