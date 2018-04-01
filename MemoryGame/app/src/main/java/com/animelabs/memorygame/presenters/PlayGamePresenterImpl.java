package com.animelabs.memorygame.presenters;

import com.animelabs.memorygame.R;
import com.animelabs.memorygame.interactors.PlayGameInteractor;
import com.animelabs.memorygame.network.models.ApiResponse;
import com.animelabs.memorygame.network.models.ImageItems;
import com.animelabs.memorygame.views.PlayGameView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asheeshsharma on 01/04/18.
 * PlayGamePresenterImpl
 */

public class PlayGamePresenterImpl implements PlayGamePresenter, PlayGameInteractor.OnFinishedListener {

    private PlayGameView mPlayGameView;
    private PlayGameInteractor mPlayGameInteractor;

    @Override
    public void attach(PlayGameView playGameView, PlayGameInteractor playGameInteractor) {
        this.mPlayGameView =playGameView;
        this.mPlayGameInteractor = playGameInteractor;
    }

    @Override
    public void fetchItems() {
        if(mPlayGameView != null && mPlayGameInteractor != null) {
            mPlayGameInteractor.findItems(this);
        }
    }

    @Override
    public void destroy() {
        mPlayGameView = null;
        mPlayGameInteractor = null;
    }

    @Override
    public void onItemClicked(int totalCount, ImageItems imageItems1, ImageItems imageItems2, int position) {
        if (totalCount < 2) {
            if (imageItems1.getMedia().getM().contentEquals(imageItems2.getMedia().getM())) {
                mPlayGameView.showMessage(R.string.win_message);
                mPlayGameView.onWinGame(position);
            } else {
                mPlayGameView.onLostGame(position);
                String text = "";
                if (totalCount == 0) {
                    text = "Uh Oh! Last Chance";
                } else {

                    text = "Please Hit Refresh to Play Again!";
                }
                mPlayGameView.showMessage(text);
            }
        }
    }

    @Override
    public void onFinished(ApiResponse apiResponse) {
        List<ImageItems> imageItems = apiResponse.getItems();
        if(mPlayGameView != null) {
            if(imageItems.size() >= 9) {
                List<ImageItems> subList = new ArrayList<ImageItems>(imageItems.subList(0, 9));
                mPlayGameView.setData(subList);
            }else {
                mPlayGameView.showMessage(R.string.error_items);
            }
            mPlayGameView.hideProgress();
        }

    }



    @Override
    public void onError(String error) {
        mPlayGameView.hideProgress();
        mPlayGameView.showMessage(R.string.error_items);
    }

    @Override
    public void onStarted() {
        mPlayGameView.showProgress();
    }
}
