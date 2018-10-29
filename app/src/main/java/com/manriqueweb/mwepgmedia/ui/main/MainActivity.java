package com.manriqueweb.mwepgmedia.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.manriqueweb.mwepgmedia.R;
import com.manriqueweb.mwepgmedia.model.Channel;
import com.manriqueweb.mwepgmedia.model.Epg;
import com.manriqueweb.mwepgmedia.model.Schedule;
import com.manriqueweb.mwepgmedia.network.IApiResponse;
import com.manriqueweb.mwepgmedia.network.NetworkWrapper;
import com.manriqueweb.mwepgmedia.ui.commons.BaseActivity;
import com.manriqueweb.mwepgmedia.ui.main.components.EPGDataImpl;
import com.manriqueweb.mwepgmedia.ui.main.components.EpgView;
import com.manriqueweb.mwepgmedia.ui.main.components.EpgViewListener;
import com.manriqueweb.mwepgmedia.utils.Constants;

public class MainActivity extends BaseActivity {
    //region private variables
    private static final String LOG_TAG = Constants.STR_LOG_TAG.concat("MainActivity: ");

    private EpgView mEpgView;
    //endregion

    //region main activity methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEpgView = findViewById(R.id.epg);
        mEpgView.setEPGClickListener(new EpgViewListener() {
            @Override
            public void onChannelClicked(int channelPosition, Channel epgChannel) {
                Toast.makeText(MainActivity.this, epgChannel.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScheduleClicked(int channelPosition, int programPosition, Schedule epgEvent) {
                Toast.makeText(MainActivity.this, epgEvent.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResetButtonClicked() {
                mEpgView.recalculateAndRedraw(true);
            }
        });

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

    }
    //endregion

}
