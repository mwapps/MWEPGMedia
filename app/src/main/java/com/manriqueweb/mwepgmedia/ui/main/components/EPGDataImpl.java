package com.manriqueweb.mwepgmedia.ui.main.components;

import android.util.Log;

import com.manriqueweb.mwepgmedia.model.Channel;
import com.manriqueweb.mwepgmedia.model.Epg;
import com.manriqueweb.mwepgmedia.model.Schedule;
import com.manriqueweb.mwepgmedia.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kristoffer on 15-05-23.
 * https://github.com/korre/android-tv-epg
 */
public class EPGDataImpl implements EPGData {
    private static final String LOG_TAG = Constants.STR_LOG_TAG.concat(EPGDataImpl.class.getSimpleName());

    private List<Channel> channels = new ArrayList<>();
    private List<List<Schedule>> events = new ArrayList<>();
    private boolean isDebbud = false;

    public EPGDataImpl(Epg data, boolean isDebbud) {
        this.isDebbud = isDebbud;
        if(data!=null && data.getChannels()!=null){
            for(Channel item : data.getChannels()){
                if(isDebbud){
                    Log.d(LOG_TAG, "Channel: "+item.getTitle()+", programs: "+item.getSchedules().size());
                    for(Schedule itemSchedule : item.getSchedules()){
                        Log.d(LOG_TAG, "Schedule: "+itemSchedule.getTitle()+" - "+itemSchedule.getStart().toString());
                    }
                    Log.d(LOG_TAG, "end Channel: ");
                }

                channels.add(item);
                if(item.getSchedules()!=null){
                    events.add(item.getSchedules());
                }
            }
        }
    }

    @Override
    public Channel getChannel(int position) {
        if(isDebbud){
            Log.d(LOG_TAG, "getChannel: position: "+position);
        }
        return channels.get(position);
    }

    @Override
    public List<Schedule> getSchedule(int channelPosition) {
        if(isDebbud){
            Log.d(LOG_TAG, "getSchedule: channelPosition: "+channelPosition);
        }
        return events.get(channelPosition);
    }

    @Override
    public Schedule getEvent(int channelPosition, int programPosition) {
        if(isDebbud){
            Log.d(LOG_TAG, "getEvent: channelPosition: "+channelPosition+", programPosition: "+programPosition);
        }
        return events.get(channelPosition).get(programPosition);
    }

    @Override
    public int getChannelCount() {
        if(isDebbud){
            Log.d(LOG_TAG, "getChannelCount: "+channels.size());
        }
        return channels.size();
    }

    @Override
    public boolean hasData() {
        if(isDebbud){
            Log.d(LOG_TAG, "hasData: "+!channels.isEmpty());
        }
        return !channels.isEmpty();
    }
}
