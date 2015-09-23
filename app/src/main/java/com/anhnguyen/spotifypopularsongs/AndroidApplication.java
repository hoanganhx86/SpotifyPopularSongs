package com.anhnguyen.spotifypopularsongs;

import com.anhnguyen.spotifypopularsongs.di.components.ApplicationComponent;
import com.anhnguyen.spotifypopularsongs.di.components.DaggerApplicationComponent;
import com.anhnguyen.spotifypopularsongs.di.modules.ApplicationModule;

import android.app.Application;

/**
 * Android Main Application
 */
public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
