package com.anhnguyen.spotifypopularsongs.di.modules;

import com.anhnguyen.data.executor.JobExecutor;
import com.anhnguyen.data.repository.SongDataRepository;
import com.anhnguyen.spotifypopularsongs.AndroidApplication;
import com.anhnguyen.spotifypopularsongs.UIThread;
import com.anhnguyen.spotifypopularsongs.navigation.Navigator;
import com.domain.executor.PostExecutionThread;
import com.domain.executor.ThreadExecutor;
import com.domain.repository.SongRepository;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {

    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    SongRepository provideSongRepository(SongDataRepository songRepository) {
        return songRepository;
    }

}
