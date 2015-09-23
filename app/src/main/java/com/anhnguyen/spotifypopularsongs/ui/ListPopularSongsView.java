package com.anhnguyen.spotifypopularsongs.ui;

import com.anhnguyen.spotifypopularsongs.model.SongModel;

import java.util.List;

public interface ListPopularSongsView extends LoadDataView {

    void renderListPopularSongs(List<SongModel> songs);

    void onFailedLoadData(String error);

    void viewSong(SongModel song);

}
