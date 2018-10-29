package com.manriqueweb.mwepgmedia.network;

import com.manriqueweb.mwepgmedia.model.Epg;

public class NetworkWrapper {
    private static final NetworkWrapper ourInstance = new NetworkWrapper();

    private static ApiRestClient mApiRestClient = new ApiRestClient();

    public static NetworkWrapper getInstance() {
        return ourInstance;
    }

    private NetworkWrapper() {
    }

    public void getEpgDataAsync(final IApiResponse<Epg> mOnEpgResponse) {
        mApiRestClient.getEpgDataAsync(mOnEpgResponse);
    }

}
