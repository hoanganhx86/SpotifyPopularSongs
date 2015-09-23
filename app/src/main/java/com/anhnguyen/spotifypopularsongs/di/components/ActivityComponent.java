package com.anhnguyen.spotifypopularsongs.di.components;

import com.anhnguyen.spotifypopularsongs.di.PerActivity;
import com.anhnguyen.spotifypopularsongs.di.modules.ActivityModule;

import android.support.v7.app.AppCompatActivity;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    //Exposed to sub-graphs.
    AppCompatActivity activity();

}
