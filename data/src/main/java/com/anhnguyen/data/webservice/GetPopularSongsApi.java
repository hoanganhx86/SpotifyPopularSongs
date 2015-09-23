package com.anhnguyen.data.webservice;

import com.anhnguyen.data.entity.SongEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit.http.GET;
import retrofit.http.Headers;

public interface GetPopularSongsApi {

    class PopularSongResponse{
        String prevDate;
        List<TrackResponse> tracks;

        public List<SongEntity> map(){
            ArrayList<SongEntity> results = new ArrayList<>();
            for(TrackResponse story : tracks){
                results.add(story.map());
            }
            return results;
        }
    }

    class TrackResponse {
        private String date;
        private String country;
        private String track_url;
        private String track_name;
        private String artist_name;
        private String artist_url;
        private String album_name;
        private String album_url;
        private String artwork_url;
        private String num_streams;
        private String window_type;

        public SongEntity map() {
            SongEntity songEntity = new SongEntity();
            songEntity.setAlbumName(album_name);
            songEntity.setAlbumUrl(album_url);
            songEntity.setArtistName(artist_name);
            songEntity.setArtworkUrl(artwork_url);
            songEntity.setArtistUrl(artist_url);
            songEntity.setCountry(country);
            songEntity.setDate(date);
            songEntity.setNumStreams(num_streams);
            songEntity.setTrackName(track_name);
            songEntity.setTrackUrl(track_url);
            songEntity.setWindowType(window_type);

            return songEntity;
        }
    }


    @Headers({
        "Content-Type: application/json;charset=UTF-8"
    })
    @GET("/api/tracks/most_streamed/global/daily/latest")
    PopularSongResponse getPopularSongs();

}
