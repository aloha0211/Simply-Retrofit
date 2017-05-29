package com.treehouse.android.retrofitworkshop.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LuongHH on 5/29/2017.
 */

public class Service {

    public static Imgur.Auth getAthedApi() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request authed = chain.request()
                                .newBuilder()
                                .addHeader("Authorization", "Bearer " + OAuthUtil.get(OAuthUtil.ACCESS_TOKEN))
                                .build();
                        return chain.proceed(authed);
                    }
                }).build();

        return new Retrofit.Builder()
                .baseUrl(Imgur.IMGUR_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Imgur.Auth.class);
    }

    public static Imgur.Anon getAnonApi() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request authed = chain.request()
                                .newBuilder()
                                .addHeader("Authorization", "Client-ID " + Imgur.IMGUR_CLIENT_ID)
                                .build();
                        return chain.proceed(authed);
                    }
                }).build();

        return new Retrofit.Builder()
                .baseUrl(Imgur.IMGUR_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Imgur.Anon.class);
    }
}
