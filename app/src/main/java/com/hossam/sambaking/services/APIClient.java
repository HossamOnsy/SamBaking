package com.hossam.sambaking.services;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Hossam.Onsy on 6/18/2017.
 */

public class APIClient extends AppCompatActivity {
    public static final String BASEURL = "https://d17h27t6h515a5.cloudfront.net";

    private static Retrofit retrofit = null;

    public static Retrofit getClient( Context context) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();



        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit;
    }

}

