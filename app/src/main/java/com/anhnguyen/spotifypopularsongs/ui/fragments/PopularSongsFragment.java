package com.anhnguyen.spotifypopularsongs.ui.fragments;

import com.anhnguyen.spotifypopularsongs.R;
import com.anhnguyen.spotifypopularsongs.di.components.SongComponent;
import com.anhnguyen.spotifypopularsongs.model.SongModel;
import com.anhnguyen.spotifypopularsongs.presenter.PopularSongsPresenter;
import com.anhnguyen.spotifypopularsongs.ui.ListPopularSongsView;
import com.anhnguyen.spotifypopularsongs.ui.adapters.PopularSongsAdapter;
import com.pnikosis.materialishprogress.ProgressWheel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PopularSongsFragment extends BaseFragment implements ListPopularSongsView{

    // ===========================================================
    // Constants
    // ===========================================================

    private static final String TAG = "UBLFeedsFragment";

    // ===========================================================
    // Fields
    // ===========================================================

    @Inject
    PopularSongsPresenter popularSongsPresenter;

    private PopularSongViewListener feedsViewListener;

    @Bind(R.id.lv)
    ListView listView;


    @Bind(R.id.progress_wheel)
    ProgressWheel progressWheel;
    @Bind(R.id.swrl)
    SwipeRefreshLayout swrl;

    private PopularSongsAdapter popularSongsAdapter;

    // ===========================================================
    // Constructors
    // ===========================================================

    public PopularSongsFragment() {
        super();
    }

    public static PopularSongsFragment newInstance() {
        PopularSongsFragment popularSongsFragment = new PopularSongsFragment();
        return popularSongsFragment;
    }

    // ===========================================================
    // Methods from SuperClass/Interfaces
    // ===========================================================


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof PopularSongViewListener){
            this.feedsViewListener = (PopularSongViewListener)activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.popular_songs_fragment, container, false);
        ButterKnife.bind(this, fragmentView);
        setupUI();

        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.popularSongsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.popularSongsPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.popularSongsPresenter.destroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initialize() {
        this.getComponent(SongComponent.class).inject(this);
        this.popularSongsPresenter.setView(this);
        this.popularSongsPresenter.initializeAndLoadData();
    }

    private void setupUI() {

        swrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (popularSongsPresenter != null) {
                    popularSongsPresenter.initializeAndLoadData();
                } else {
                    swrl.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void showLoading() {
        if(progressWheel.getVisibility() == View.GONE && ! swrl.isRefreshing()) progressWheel.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressWheel.setVisibility(View.GONE);
        if(swrl != null && swrl.isRefreshing()){
            swrl.setRefreshing(false);
        }
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        this.showSnackBarMessage(message);
    }

    @Override
    public Context getContext() {
        return this.getActivity().getApplicationContext();
    }

    @Override
    public void renderListPopularSongs(List<SongModel> storyModels) {

    }

    @Override
    public void onFailedLoadData(String error) {

    }

    @Override
    public void viewSong(SongModel songModel) {
        if (this.feedsViewListener != null) {
            this.feedsViewListener.onUserClicked(songModel);
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================


    // ===========================================================
    // Inner class
    // ===========================================================

    /**
     * Interface for listening user list events.
     */
    public interface PopularSongViewListener {

        void onUserClicked(final SongModel songModel);

    }

}
