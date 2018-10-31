package com.manriqueweb.mwepgmedia.ui.commons;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.manriqueweb.mwepgmedia.BuildConfig;
import com.manriqueweb.mwepgmedia.utils.Constants;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {
    protected static final String LOG_TAG = Constants.STR_LOG_TAG.concat(BaseActivity.class.getSimpleName());

    protected FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        fragmentManager = getSupportFragmentManager();

        if(isDebbud()){
            Log.d(LOG_TAG, "onCreate getLocalClassName(): " + BaseActivity.class.getSimpleName());
        }
    }

    protected abstract int getLayoutId();

    protected boolean isDebbud(){
        return BuildConfig.DEBUG;
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void showMsgSnackbar(View view, CharSequence messageToShow, int SnackbarLength){
        if(isDebbud()){
            Log.d(LOG_TAG, "showMsgSnackbar: " + messageToShow);
        }
        Snackbar.make(view, messageToShow, SnackbarLength).show();
    }

    protected void showMsgSnackbarAndAction(View view, CharSequence messageToShow, int SnackbarLength, CharSequence messageToAction, View.OnClickListener mOnClickListener){
        Snackbar.make(view, messageToShow, SnackbarLength)
                .setAction(messageToAction, mOnClickListener)
                .show();
    }

    protected BaseFragment getCurrentContentFragment()
    {
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null)
        {
            for(Fragment fragment : fragments)
            {
                if(fragment != null && fragment.isVisible() && fragment instanceof BaseFragment)
                {
                    return (BaseFragment) fragment;
                }
            }
        }
        return null;
    }

    protected void replaceFragment(int IdframeLayout, @NonNull Fragment fragment, @NonNull String tag) {
        if(isDebbud()){
            Log.d(LOG_TAG, "tag: " + tag);
        }

        if (!fragment.equals(getCurrentContentFragment())) {
            fragmentManager
                    .beginTransaction()
                    .replace(IdframeLayout, fragment, tag)
                    .commit();
        }
    }
}
