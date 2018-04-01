package com.animelabs.memorygame.network;

import com.animelabs.memorygame.network.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by asheeshsharma on 01/04/18.
 */

public interface ApiInterface {
    @GET("services/feeds/photos_public.gne")
    Call<ApiResponse> getRandomPhotos(@Query("format") String format, @Query("nojsoncallback") int nojsoncallback);
}