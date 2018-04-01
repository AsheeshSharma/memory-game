package com.animelabs.memorygame.views;

import com.animelabs.memorygame.network.models.ImageItems;

import java.util.List;

/**
 * Created by asheeshsharma on 01/04/18.
 */

public interface PlayGameView {
    void showProgress();
    void hideProgress();
    void setData(List<ImageItems> imageItems);
    void showMessage(int id);
    void showMessage(String msg);
    void onWinGame(int position);
    void onLostGame(int position);
}
