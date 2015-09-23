package com.anhnguyen.spotifypopularsongs.ui.activities;

import com.anhnguyen.spotifypopularsongs.AndroidApplication;
import com.anhnguyen.spotifypopularsongs.di.components.ApplicationComponent;
import com.anhnguyen.spotifypopularsongs.di.modules.ActivityModule;
import com.anhnguyen.spotifypopularsongs.navigation.Navigator;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG_LOADING = "Loading";
    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
        this.overridePendingTransition(0, 0); // remove activity transition animation
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(0, 0); // remove activity transition animation
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        String tag =  String.format(Locale.US, "FRAGMENT_TAG_%s", fragment.getClass().getName()).toUpperCase();
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment, tag);
        fragmentTransaction.commit();
    }

    /**
     * replaces a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void replaceFragment(int containerViewId, Fragment fragment) {
        replaceFragment(containerViewId, fragment, false);
    }

    /**
     * replaces a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, boolean addToBackStack) {
        String tag =  String.format(Locale.US, "FRAGMENT_TAG_%s", fragment.getClass().getName()).toUpperCase();;
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment, tag);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    /**
     * Get the Main Application component for dependency injection.
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     **/
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }


    void showError(View view, String error) {
        Snackbar.make(view, error, Snackbar.LENGTH_LONG).show();
    }
}
