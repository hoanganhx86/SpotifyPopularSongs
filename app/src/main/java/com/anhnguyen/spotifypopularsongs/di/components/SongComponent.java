package com.anhnguyen.spotifypopularsongs.di.components;

import com.anhnguyen.spotifypopularsongs.di.PerActivity;
import com.anhnguyen.spotifypopularsongs.di.modules.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class })
public interface SongComponent extends ActivityComponent {


}
