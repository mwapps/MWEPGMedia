package com.manriqueweb.mwepgmedia.ui.main.components;
import com.manriqueweb.mwepgmedia.model.Channel;
import com.manriqueweb.mwepgmedia.model.Schedule;

import java.util.List;


/**
 * Interface to implement and pass to EPG containing data to be used.
 * Implementation can be a simple as simple as a Map/List or maybe an Adapter.
 * Created by Kristoffer on 15-05-23.
 * https://github.com/korre/android-tv-epg
 */
public interface EPGData {
    Channel getChannel(int position);

    List<Schedule> getSchedule(int channelPosition);

    Schedule getEvent(int channelPosition, int programPosition);

    int getChannelCount();

    boolean hasData();
}
