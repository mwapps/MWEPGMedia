package com.manriqueweb.mwepgmedia.network;

import com.manriqueweb.mwepgmedia.model.Epg;
import com.manriqueweb.mwepgmedia.model.Program;
import com.manriqueweb.mwepgmedia.model.Slides;

public class NetworkWrapper {
    private static final NetworkWrapper ourInstance = new NetworkWrapper();

    private static ApiRestClient mApiRestClient = ApiRestClient.getInstance();

    public static NetworkWrapper getInstance() {
        return ourInstance;
    }

    private NetworkWrapper() {
    }

    public void getEpgDataAsync(final IApiResponse<Epg> mOnEpgResponse) {
        mApiRestClient.getEpgDataAsync(mOnEpgResponse);
    }

    public void getProgramDataAsync(String program_id, final IApiResponse<Program> mOnProgramResponse) {
        mApiRestClient.getProgramDataAsync(program_id, mOnProgramResponse);
    }

    public void getSlidesDataAsync(final IApiResponse<Slides> mOnSlidesResponse) {
        mApiRestClient.getSlidesDataAsync(mOnSlidesResponse);
    }
}
