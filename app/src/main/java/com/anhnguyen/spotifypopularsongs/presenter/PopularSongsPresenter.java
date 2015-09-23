package com.anhnguyen.spotifypopularsongs.presenter;

import com.anhnguyen.spotifypopularsongs.di.PerActivity;
import com.anhnguyen.spotifypopularsongs.exception.ErrorMessageFactory;
import com.anhnguyen.spotifypopularsongs.mapper.SongModelDataMapper;
import com.anhnguyen.spotifypopularsongs.model.SongModel;
import com.anhnguyen.spotifypopularsongs.ui.ListPopularSongsView;
import com.anhnguyen.spotifypopularsongs.utils.ILog;
import com.domain.Song;
import com.domain.exception.DefaultErrorBundle;
import com.domain.exception.ErrorBundle;
import com.domain.interactor.DefaultSubscriber;
import com.domain.interactor.GetPopularSongsUseCase;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

@PerActivity
public class PopularSongsPresenter extends DefaultSubscriber<List<SongModel>> implements Presenter{

    // ===========================================================
    // Constants
    // ===========================================================

    private static final String TAG = "UBLFeedsPresenter";

    // ===========================================================
    // Fields
    // ===========================================================

    private final GetPopularSongsUseCase getPopularSongsUseCase;
    private ListPopularSongsView listPopularSongsView;

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    public PopularSongsPresenter(GetPopularSongsUseCase getPopularSongsUseCase) {
        this.getPopularSongsUseCase = getPopularSongsUseCase;
    }

    // ===========================================================
    // Methods from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getPopularSongsUseCase.unsubscribe();
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public void setView(@NonNull ListPopularSongsView view) {
        this.listPopularSongsView = view;
    }

    // ===========================================================
    // Methods
    // ===========================================================

    private void loadData() {
        this.getPopularSongsUseCase.execute(new LoadPopularSongsSubscriber());
    }

    public void initializeAndLoadData() {
        this.hideViewRetry();
        this.showViewLoading();
        this.loadData();
    }

    public void onUserClicked(SongModel songModel) {
        if(this.listPopularSongsView != null) this.listPopularSongsView.viewSong(songModel);
    }


    private void showViewLoading() {
        this.listPopularSongsView.showLoading();
    }

    private void hideViewLoading() {
        this.listPopularSongsView.hideLoading();
    }

    private void showViewRetry() {
        this.listPopularSongsView.showRetry();
    }

    private void hideViewRetry() {
        this.listPopularSongsView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle UBLErrorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.listPopularSongsView.getContext(), UBLErrorBundle.getException());
        this.listPopularSongsView.showError(errorMessage);
    }

    private final class LoadPopularSongsSubscriber extends DefaultSubscriber<List<Song>> {

        @Override
        public void onCompleted() {
            PopularSongsPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            PopularSongsPresenter.this.hideViewLoading();
            PopularSongsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            PopularSongsPresenter.this.listPopularSongsView.onFailedLoadData(e.getMessage());
        }

        @Override
        public void onNext(List<Song> songs) {
            PopularSongsPresenter.this.listPopularSongsView.renderListPopularSongs(SongModelDataMapper.transformSongs(songs));
            ILog.d("LoadPopularSongsSubscriber", " Success LoadPopularSongsSubscriber size " + songs.size());
        }
    }

}
