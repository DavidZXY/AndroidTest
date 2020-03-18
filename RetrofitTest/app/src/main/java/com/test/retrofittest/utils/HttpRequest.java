package com.test.retrofittest.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.test.retrofittest.Constant.API_URL;

/**
 * @author DavidZXY
 * @date 2019/12/18
 */
public class HttpRequest {

    public static final Retrofit BASE_RETROFIT = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
