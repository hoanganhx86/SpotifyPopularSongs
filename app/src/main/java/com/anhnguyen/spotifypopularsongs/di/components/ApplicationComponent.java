package com.anhnguyen.spotifypopularsongs.di.components;

import com.anhnguyen.spotifypopularsongs.di.modules.ApplicationModule;
import com.anhnguyen.spotifypopularsongs.ui.activities.BaseActivity;
import com.anhnguyen.spotifypopularsongs.ui.activities.MainActivity;
import com.anhnguyen.spotifypopularsongs.ui.fragments.BaseFragment;
import com.domain.executor.PostExecutionThread;
import com.domain.executor.ThreadExecutor;
import com.domain.repository.SongRepository;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);
    void inject(MainActivity mainActivity);
    void inject(BaseFragment baseFragment);

    //Exposed to sub-graphs.
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    SongRepository songRepository();


}
