package com.anhnguyen.spotifypopularsongs.ui.fragments;

import com.anhnguyen.spotifypopularsongs.AndroidApplication;
import com.anhnguyen.spotifypopularsongs.Configs;
import com.anhnguyen.spotifypopularsongs.di.HasComponent;
import com.anhnguyen.spotifypopularsongs.di.components.ApplicationComponent;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Base {@link android.app.Fragment} class for every fragment in this application.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
        setRetainInstance(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    /**
     * Get the Main Application component for dependency injection.
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getActivity().getApplication()).getApplicationComponent();
    }

    /**
     * Shows a {@link Snackbar} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showSnackBarMessage(String message) {
        if(Configs.DEBUG){
            Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
        }
    }
    /**
     * Shows a {@link Snackbar} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showSnackBarMessage(String message, CharSequence action, final View.OnClickListener listener) {
        if(Configs.DEBUG){
            Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).setAction(action, listener).show();
        }
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
