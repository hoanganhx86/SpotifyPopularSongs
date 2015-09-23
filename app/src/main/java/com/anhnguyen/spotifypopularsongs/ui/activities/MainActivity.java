package com.anhnguyen.spotifypopularsongs.ui.activities;

import com.anhnguyen.spotifypopularsongs.R;

import android.os.Bundle;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setupUI();

        getApplicationComponent().inject(this);

        initializeActivity(savedInstanceState);
        initializeInjector();
    }

    private void initializeInjector() {


    }

    private void initializeActivity(Bundle savedInstanceState) {

    }

    private void setupUI() {


    }

}
