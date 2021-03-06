package com.treehouse.android.retrofitworkshop.api;

import com.treehouse.android.retrofitworkshop.model.Basic;
import com.treehouse.android.retrofitworkshop.model.Image;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by LuongHH on 5/29/2017.
 */

public interface Imgur {

    String IMGUR_BASE_URL = "https://api.imgur.com";
    String IMGUR_CLIENT_ID = "fd1d4e07333bc19";
    String AUTHORIZATION_URL = "https://api.imgur.com/oauth2/authorize?client_id=" + IMGUR_CLIENT_ID + "&response_type=token";
    String REDIRECT_URI = "https://treehouseworkshop:88";

    interface Auth {
        @GET("3/account/{username}/images/{page}")
        Call<Basic<ArrayList<Image>>> images(@Path("username") String username, @Path("page") int page);

        @Multipart
        @POST("3/upload")
        Call<Basic<Image>> uploadImages(@Part("image") RequestBody image);
    }

    interface Anon {

        @Multipart
        @POST("3/upload")
        Call<Basic<Image>> uploadImages(@Part("image") RequestBody image);
    }
}
