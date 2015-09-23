package com.anhnguyen.spotifypopularsongs.ui;

import com.anhnguyen.spotifypopularsongs.model.SongModel;

import java.util.List;

public interface ListPopularSongsView extends LoadDataView {

    void renderListPopularSongs(List<SongModel> songModels);

    void onFailedLoadData(String error);

    void viewSong(SongModel songModel);

}
