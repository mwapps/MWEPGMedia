package com.manriqueweb.mwepgmedia.network;

public interface IApiResponse<T> {
    void onResponse(T data);
    void onError(String errorMsg);
}
