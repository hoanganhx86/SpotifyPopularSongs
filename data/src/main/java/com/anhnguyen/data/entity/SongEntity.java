package com.anhnguyen.data.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SongEntity extends RealmObject {

    // ===========================================================
    // Fields
    // ===========================================================

    // !!! NOTE: Due to the models are changed frequently we Just keep all fields as String for easily init
    // Every time has something changed you need to take care models on other layers and data mappers too
    // TODO: Refactor

    @PrimaryKey
    private String trackName;
    private String date;
    private String country;
    private String trackUrl;
    private String artistName;
    private String artistUrl;
    private String albumName;
    private String albumUrl;
    private String artworkUrl;
    private String numStreams;
    private String windowType;

    // ===========================================================
    // Getter & Setter
    // ===========================================================


    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTrackUrl() {
        return trackUrl;
    }

    public void setTrackUrl(String trackUrl) {
        this.trackUrl = trackUrl;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistUrl() {
        return artistUrl;
    }

    public void setArtistUrl(String artistUrl) {
        this.artistUrl = artistUrl;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumUrl() {
        return albumUrl;
    }

    public void setAlbumUrl(String albumUrl) {
        this.albumUrl = albumUrl;
    }

    public String getArtworkUrl() {
        return artworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        this.artworkUrl = artworkUrl;
    }

    public String getNumStreams() {
        return numStreams;
    }

    public void setNumStreams(String numStreams) {
        this.numStreams = numStreams;
    }

    public String getWindowType() {
        return windowType;
    }

    public void setWindowType(String windowType) {
        this.windowType = windowType;
    }


}
