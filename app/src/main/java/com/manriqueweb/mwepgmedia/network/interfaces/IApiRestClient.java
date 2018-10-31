package com.manriqueweb.mwepgmedia.network.interfaces;

import com.manriqueweb.mwepgmedia.model.Epg;
import com.manriqueweb.mwepgmedia.model.Program;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface IApiRestClient {
    @Headers("Content-Type: application/json")
    @GET("epg")
    Call<Epg> getEpg();

    @Headers("Content-Type: application/json")
    @GET("program/{id}")
    Call<Program> getProgram(@Path("id") String id);
}
