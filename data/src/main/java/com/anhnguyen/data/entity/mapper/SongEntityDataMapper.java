package com.anhnguyen.data.entity.mapper;

import com.anhnguyen.data.entity.SongEntity;
import com.domain.Song;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link SongEntity} (in the data layer) to {@link Song} in the
 * domain layer.
 */
@Singleton
public class SongEntityDataMapper {

    private static final String TAG = "SongEntityDataMapper";

    private Context context;

    @Inject
    public SongEntityDataMapper(Context context) {
        this.context = context;
    }

    /**
     * Transform a {@link com.anhnguyen.data.entity.SongEntity} into an {@link com.domain.Song}.
     *
     * @param songEntity Object to be transformed.
     * @return {@link com.domain.Song} if valid {@link com.anhnguyen.data.entity.SongEntity} otherwise null.
     */
    public static Song transformSong(SongEntity songEntity) {
        Song song = null;
        if (songEntity != null) {
            song = new Song();

        }

        return song;
    }

    /**
     * Transform a List of {@link SongEntity} into a Collection of {@link Song}.
     *
     * @param songEntityCollection Object Collection to be transformed.
     * @return {@link Song} if valid {@link SongEntity} otherwise null.
     */
    public static List<Song> transformSongs(Collection<SongEntity> songEntityCollection) {
        List<Song> songs = new ArrayList<>();
        Song song;
        for (SongEntity songEntity : songEntityCollection) {
            song = transformSong(songEntity);
            if (song != null) {
                songs.add(song);
            }
        }

        return songs;
    }



}
