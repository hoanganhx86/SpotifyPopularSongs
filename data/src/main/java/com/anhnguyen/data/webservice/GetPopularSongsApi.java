package com.anhnguyen.data.webservice;

import com.google.gson.annotations.SerializedName;

import com.anhnguyen.data.entity.SongEntity;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Headers;

public interface GetPopularSongsApi {

    class StoryResponse {

        @SerializedName("_id")
        public String id;

        @SerializedName("created_date")
        public String createdDate;

        @SerializedName("is_gone")
        public String isGone;

        @SerializedName("tag")
        public String tag;

        @SerializedName("tags")
        public List<String> tags;

        @SerializedName("original")
        public String original;

        public SongEntity map() {
            SongEntity songEntity = new SongEntity();

            return songEntity;
        }
    }


    @Headers({
        "Content-Type: application/json;charset=UTF-8"
    })
    @GET("/api/tracks/most_streamed/global/daily/latest")
    List<StoryResponse> getPopularSongs();

}
