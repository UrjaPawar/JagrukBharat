package com.urjapawar.jagrukbharat;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface StateAPI {
    @FormUrlEncoded
    @POST("/getData/")
     void insertTask(
            @Field("state") String state,
            Callback<Response> serverResponseCallback);
}
