package com.anhnguyen.spotifypopularsongs.ui.activities;

import com.anhnguyen.spotifypopularsongs.R;
import com.anhnguyen.spotifypopularsongs.di.HasComponent;
import com.anhnguyen.spotifypopularsongs.di.components.DaggerSongComponent;
import com.anhnguyen.spotifypopularsongs.di.components.SongComponent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements HasComponent<SongComponent> {

    private SongComponent songComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getApplicationComponent().inject(this);

        initializeInjector();
    }

    private void initializeInjector() {
        this.songComponent = DaggerSongComponent.builder()
            .applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .build();

    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public SongComponent getComponent() {
        return songComponent;
    }
}
