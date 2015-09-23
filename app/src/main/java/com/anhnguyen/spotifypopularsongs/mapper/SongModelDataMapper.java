/**
 * Umbala
 *
 * Created by Hoang Anh on 11/8/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */
package com.anhnguyen.spotifypopularsongs.mapper;

import com.anhnguyen.spotifypopularsongs.model.SongModel;
import com.domain.Song;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Mapper class used to transformSong data (in the domain layer) tdata in the
 * presentation layer.
 */
public class SongModelDataMapper {

    private static final String TAG = "UBLUserModelDataMapper";

    /**
     * Transform a {@link com.domain.Song} into an {@link com.anhnguyen.spotifypopularsongs.model.SongModel}.
     *
     * @param  song Object to be transformed.
     * @return {@link com.anhnguyen.spotifypopularsongs.model.SongModel}.
     */
    public static SongModel transformSong(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Cannot transformSong a null value");
        }

        SongModel songModel = new SongModel();

        return songModel;
    }

    /**
     * Transform a Collection of {@link Song} into a Collection of {@link SongModel}.
     *
     * @param songsCollection Objects to be transformed.
     * @return List of {@link SongModel}.
     */
    public static List<SongModel> transformUsers(Collection<Song> songsCollection) {
        ArrayList<SongModel> songModels;

        if (songsCollection != null && ! songsCollection.isEmpty()) {
            songModels = new ArrayList<>();
            for (Song song : songsCollection) {
                songModels.add(transformSong(song));
            }
        } else {
            songModels = new ArrayList<>();
        }

        return songModels;
    }

}
