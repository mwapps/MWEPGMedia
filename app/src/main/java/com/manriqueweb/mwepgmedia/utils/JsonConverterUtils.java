package com.manriqueweb.mwepgmedia.utils;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manriqueweb.mwepgmedia.model.Epg;
import com.manriqueweb.mwepgmedia.model.Program;
import com.manriqueweb.mwepgmedia.model.Slides;

import java.io.IOException;

public abstract class JsonConverterUtils {
    //region private variables
    private static final String LOG_TAG = Constants.STR_LOG_TAG.concat("JsonConverterUtils: ");
    //endregion

    //region EPG
    public static String getEpgJson(Epg mEpg){
        if(mEpg==null)
            return null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(mEpg);
        } catch (JsonProcessingException e) {
            printStackTrace(e.getStackTrace());
        }

        return null;
    }

    public static Epg getEpgFromJson(String mEpg){
        if(mEpg==null || mEpg.isEmpty())
            return null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(mEpg, Epg.class);
        } catch (IOException ioe) {
            printStackTrace(ioe.getStackTrace());
        }

        return null;
    }
    //endregion

    //region Program
    public static String getProgramJson(Program mProgram){
        if(mProgram==null)
            return null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(mProgram);
        } catch (JsonProcessingException e) {
            printStackTrace(e.getStackTrace());
        }

        return null;
    }

    public static Program getProgramFromJson(String mProgram){
        if(mProgram==null || mProgram.isEmpty())
            return null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(mProgram, Program.class);
        } catch (IOException ioe) {
            printStackTrace(ioe.getStackTrace());
        }

        return null;
    }
    //endregion

    //region Slides
    public static String getSlidesJson(Slides mSlides){
        if(mSlides==null)
            return null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(mSlides);
        } catch (JsonProcessingException e) {
            printStackTrace(e.getStackTrace());
        }

        return null;
    }

    public static Slides getSlidesFromJson(String mSlides){
        if(mSlides==null || mSlides.isEmpty())
            return null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(mSlides, Slides.class);
        } catch (IOException ioe) {
            printStackTrace(ioe.getStackTrace());
        }

        return null;
    }
    //endregion

    //region printStackTrace
    private static void printStackTrace(StackTraceElement[] mStackTraceElement){
        StringBuilder msgLog = new StringBuilder("printStackTrace: ").append("\n\r");
        for(StackTraceElement  errorStack : mStackTraceElement){
            msgLog.append(errorStack.toString()).append("\n\r");
        }
        Log.e(LOG_TAG, msgLog.toString());
    }
    //endregion

}
