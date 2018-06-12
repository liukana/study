package com.example.admin.accessibilityservicetest.network;

import com.example.admin.accessibilityservicetest.bean.ChatBackBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by liukan on 2018/3/19.
 */

public interface TuLingRobot {

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("/openapi/api/v2")
    Call<ChatBackBean> getMsg(@Body RequestBody requestBody);
}
