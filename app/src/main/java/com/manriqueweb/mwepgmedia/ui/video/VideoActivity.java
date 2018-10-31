package com.manriqueweb.mwepgmedia.ui.video;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.devbrackets.android.exomedia.listener.OnVideoSizeChangedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.manriqueweb.mwepgmedia.R;
import com.manriqueweb.mwepgmedia.ui.commons.BaseActivity;
import com.manriqueweb.mwepgmedia.utils.Constants;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.listener.OnVideoSizeChangedListener;

public class VideoActivity extends BaseActivity implements OnPreparedListener, OnVideoSizeChangedListener {
    //region private variables
    private static final String LOG_TAG = Constants.STR_LOG_TAG.concat("VideoActivity: ");

    private VideoView videoView;
    private String videoURL;

    private FullScreenListener fullScreenListener = new FullScreenListener();
    //endregion

    //region main methods
    @Override
    public void onDestroy() {
        super.onDestroy();

        exitFullscreen();
        View decorView = getWindow().getDecorView();
        if (decorView != null) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUiFlags();

        videoURL = getIntent().getStringExtra(Constants.STR_KEY_URL);
        setupVideoView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    public void onPrepared() {
        videoView.start();
    }
    @Override
    public void onVideoSizeChanged(int intrinsicWidth, int intrinsicHeight, float pixelWidthHeightRatio) {
        goFullscreen();
    }

    private void setupVideoView() {
        videoView = findViewById(R.id.video_view);
        videoView.setOnPreparedListener(this);
        videoView.setOnVideoSizedChangedListener(this);

        videoView.setVideoURI(Uri.parse(videoURL));
    }
    //endregion

    //region setup full screen methods
    private void initUiFlags() {
        View decorView = getWindow().getDecorView();
        if (decorView != null) {
            decorView.setSystemUiVisibility(getStableUiFlags());
            decorView.setOnSystemUiVisibilityChangeListener(fullScreenListener);
        }
    }

    private int getFullscreenUiFlags() {
        return View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
    }

    private int getStableUiFlags() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
    }

    private class FullScreenListener implements View.OnSystemUiVisibilityChangeListener {
        private int lastVisibility = 0;

        @Override
        public void onSystemUiVisibilityChange(int visibility) {
            lastVisibility = visibility;
            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                videoView.showControls();

                goFullscreen();
            }
        }
    }

    private void goFullscreen() {
        setUiFlags(true);
    }

    private void exitFullscreen() {
        setUiFlags(false);
    }

    private void setUiFlags(boolean fullscreen) {
        View decorView = getWindow().getDecorView();
        if (decorView != null) {
            decorView.setSystemUiVisibility(fullscreen ? getFullscreenUiFlags() : getStableUiFlags());
        }
    }
    //endregion

}
