package com.manriqueweb.mwepgmedia.ui.commons;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manriqueweb.mwepgmedia.MwEpgApp;
import com.manriqueweb.mwepgmedia.utils.Constants;

public abstract class BaseFragment extends Fragment {
    protected static final String LOG_TAG = Constants.STR_LOG_TAG.concat(BaseFragment.class.getSimpleName());

    protected FragmentManager fragmentManager;
    protected BaseActivity mActivity;
    protected View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

        if(getActivity()!=null){
            fragmentManager = getActivity().getSupportFragmentManager();
        }

        if(isDebbud()){
            Log.d(LOG_TAG, "onCreate getSimpleName(): " + BaseFragment.class.getSimpleName());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(getLayoutId(), container, false);

        if(isDebbud()){
            Log.d(LOG_TAG, "onCreateView getSimpleName(): " + BaseFragment.class.getSimpleName());
        }
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(isDebbud()){
            Log.d(LOG_TAG, "onAttach getSimpleName(): " + BaseFragment.class.getSimpleName());
        }

        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;

            if(isDebbud()){
                Log.d(LOG_TAG, "this.mActivity.getLocalClassName(): " + this.mActivity.getLocalClassName());
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if(isDebbud()){
            Log.d(LOG_TAG, "onStart getSimpleName(): " + BaseFragment.class.getSimpleName());
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if(isDebbud()){
            Log.d(LOG_TAG, "onStop getSimpleName(): " + BaseFragment.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if(isDebbud()){
            Log.d(LOG_TAG, "onDetach getSimpleName(): " + BaseFragment.class.getSimpleName());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(isDebbud()){
            Log.d(LOG_TAG, "onDestroyView getSimpleName(): " + BaseFragment.class.getSimpleName());
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(isDebbud()){
            Log.d(LOG_TAG, "onDestroy getSimpleName(): " + BaseFragment.class.getSimpleName());
        }
    }

    protected abstract int getLayoutId();

    public abstract String getCustomTag();

    protected boolean isDebbud(){
        return mActivity != null && ((MwEpgApp) mActivity.getApplicationContext()).isDebbugMode();
    }

}
