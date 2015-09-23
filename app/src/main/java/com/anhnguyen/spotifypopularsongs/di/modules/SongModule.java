package com.anhnguyen.spotifypopularsongs.di.modules;

import com.anhnguyen.spotifypopularsongs.di.PerActivity;
import com.domain.executor.PostExecutionThread;
import com.domain.executor.ThreadExecutor;
import com.domain.interactor.GetPopularSongsUseCase;
import com.domain.repository.SongRepository;

import dagger.Module;
import dagger.Provides;


@Module
public class SongModule {

    public SongModule() {
    }

    @Provides
    @PerActivity
    GetPopularSongsUseCase provideGetUsersUseCase(SongRepository songRepository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        return new GetPopularSongsUseCase(songRepository, threadExecutor, postExecutionThread);
    }
}