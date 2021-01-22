package com.craigke.picflic.api;

import com.craigke.picflic.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit = null;
    private static final String BASE_URL = Constants.UNSPLASH_BASE_URL;

    public static Retrofit getClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

