package com.animelabs.memorygame.interactors;

import com.animelabs.memorygame.network.ApiClient;
import com.animelabs.memorygame.network.ApiInterface;
import com.animelabs.memorygame.network.models.ApiResponse;
import com.animelabs.memorygame.network.models.ImageItems;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by asheeshsharma on 01/04/18.
 */

public class PlayGameInteractorImpl implements PlayGameInteractor {
    private OnFinishedListener mListener;

    @Override
    public void findItems(OnFinishedListener listener) {
        mListener = listener;
        mListener.onStarted();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ApiResponse> call = apiService.getRandomPhotos("json", 1);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response != null && response.body() != null && response.body().getItems() != null) {
                    mListener.onFinished(response.body());
                }else {
                    mListener.onError("Error No List");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                mListener.onError(t.getMessage());
            }
        });
    }
}
