package com.anhnguyen.data.net;

import com.anhnguyen.data.entity.SongEntity;

import java.util.List;

import rx.Observable;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {

    String API_BASE_URL = "http://charts.spotify.com";

    /**
     * get popular songs
     */
    Observable<List<SongEntity>> getPopularSongs();

}
