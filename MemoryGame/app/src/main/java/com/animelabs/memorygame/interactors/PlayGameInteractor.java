package com.animelabs.memorygame.interactors;

import com.animelabs.memorygame.network.models.ApiResponse;

/**
 * Created by asheeshsharma on 01/04/18.
 */

public interface PlayGameInteractor {
    interface OnFinishedListener {
        void onFinished(ApiResponse apiResponse);
        void onError(String error);
        void onStarted();
    }
    void findItems(OnFinishedListener listener);
}
