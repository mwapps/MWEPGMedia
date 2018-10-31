package com.manriqueweb.mwepgmedia.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.manriqueweb.mwepgmedia.R;
import com.manriqueweb.mwepgmedia.model.Channel;
import com.manriqueweb.mwepgmedia.model.Epg;
import com.manriqueweb.mwepgmedia.model.Schedule;
import com.manriqueweb.mwepgmedia.model.Slide;
import com.manriqueweb.mwepgmedia.model.Slides;
import com.manriqueweb.mwepgmedia.network.IApiResponse;
import com.manriqueweb.mwepgmedia.network.NetworkWrapper;
import com.manriqueweb.mwepgmedia.ui.commons.BaseActivity;
import com.manriqueweb.mwepgmedia.ui.commons.ProgramSelectedDialog;
import com.manriqueweb.mwepgmedia.ui.main.components.EPGDataImpl;
import com.manriqueweb.mwepgmedia.ui.main.components.EpgView;
import com.manriqueweb.mwepgmedia.ui.main.components.EpgViewListener;
import com.manriqueweb.mwepgmedia.ui.main.components.ViewPagerAdapter;
import com.manriqueweb.mwepgmedia.ui.main.fragments.ProgramDetailFragment;
import com.manriqueweb.mwepgmedia.ui.video.VideoActivity;
import com.manriqueweb.mwepgmedia.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    //region private variables
    private static final String LOG_TAG = Constants.STR_LOG_TAG.concat("MainActivity: ");

    private EpgView mEpgView;
    private ViewPager viewPager;
    //endregion

    //region main activity methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEpgView = findViewById(R.id.epg);
        mEpgView.setEPGClickListener(new EpgViewListener() {
            @Override
            public void onChannelClicked(int channelPosition, Channel epgChannel) {
                channelSelected(channelPosition, epgChannel);
            }

            @Override
            public void onScheduleClicked(int channelPosition, int programPosition, Schedule epgEvent) {
                programSelected(channelPosition, programPosition, epgEvent);
            }

            @Override
            public void onResetButtonClicked() {
                mEpgView.recalculateAndRedraw(true);
            }
        });

        viewPager = findViewById(R.id.view_pager);

        makeNetworkRequest();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        if (mEpgView != null) {
            mEpgView.clearEPGImageCache();
        }
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (isDebbud()) Log.d(LOG_TAG, "requestCode: " + requestCode);
        if (isDebbud()) Log.d(LOG_TAG, "resultCode:  " + resultCode);

    }
    //endregion

    //region Network Request methods
    private void makeNetworkRequest(){

        NetworkWrapper.getInstance().getEpgDataAsync(new IApiResponse<Epg>() {
            @Override
            public void onResponse(Epg data) {
                Log.d(LOG_TAG, data.toString());

                for(Channel item : data.getChannels()){
                    if(isDebbud()){
                        Log.d(LOG_TAG, "Channel: "+item.getTitle()+", programs: "+item.getSchedules().size());
                    }
                }

                mEpgView.setEPGData(new EPGDataImpl(data, isDebbud()));
                mEpgView.recalculateAndRedraw(false);
            }

            @Override
            public void onError(String errorMsg) {
                Log.e(LOG_TAG, errorMsg);
            }
        });

        NetworkWrapper.getInstance().getSlidesDataAsync(new IApiResponse<Slides>() {
            @Override
            public void onResponse(Slides data) {
                drawSlider(data);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });

    }

    private void drawSlider(final Slides data){
        List<String> imageUrls = new ArrayList<>();
        for(Slide item : data.getSlides()){
            imageUrls.add(item.getImages().getIcon());
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);
    }
    //endregion

    //region channelSelected method
    private void channelSelected(int channelPosition, Channel epgChannel){
        Intent intent = new Intent(MainActivity.this, VideoActivity.class);
        intent.putExtra(Constants.STR_KEY_URL, "http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8");
        startActivityForResult(intent, Constants.INT_ACTIVITY_VIDEOPLAYER);
    }
    //endregion

    //region programSelected method
    private void programSelected(int channelPosition, int programPosition, final Schedule epgEvent){
        ProgramSelectedDialog pDialog = new ProgramSelectedDialog();
        Bundle bArguments = new Bundle();
        bArguments.putString(ProgramSelectedDialog.KEY_PROGRAM_ID, epgEvent.getId());
        pDialog.setArguments(bArguments);
        pDialog.setListener(new ProgramSelectedDialog.OnProgramSelectedDialogListener() {
            @Override
            public void onPlaySelected() {
                if(isDebbud()){
                    Log.d(LOG_TAG, "onPlaySelected()");
                    playVideo(epgEvent);
                }
            }

            @Override
            public void onBackSelected() {
                if(isDebbud()){
                    Log.d(LOG_TAG, "onBackSelected()");
                }
            }
        });
        pDialog.setCancelable(false);
        pDialog.show(fragmentManager, ProgramDetailFragment.class.getSimpleName());
    }
    //endregion

    //region playVideo Selected
    private void playVideo(final Schedule epgEvent){
        Intent intent = new Intent(MainActivity.this, VideoActivity.class);
        intent.putExtra(Constants.STR_KEY_URL, "http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8");
        startActivityForResult(intent, Constants.INT_ACTIVITY_VIDEOPLAYER);
    }
    //endregion
}
