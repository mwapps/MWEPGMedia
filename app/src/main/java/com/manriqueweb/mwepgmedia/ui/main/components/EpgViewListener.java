package com.manriqueweb.mwepgmedia.ui.main.components;

import com.manriqueweb.mwepgmedia.model.Channel;
import com.manriqueweb.mwepgmedia.model.Schedule;

/**
 * Created by Kristoffer on 15-05-23.
 * https://github.com/korre/android-tv-epg
 */
public interface EpgViewListener {
    void onChannelClicked(int channelPosition, Channel epgChannel);
    void onScheduleClicked(int channelPosition, int programPosition, Schedule epgSchedule);
    void onResetButtonClicked();

}
