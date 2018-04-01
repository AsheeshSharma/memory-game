package com.animelabs.memorygame.presenters;

import com.animelabs.memorygame.interactors.PlayGameInteractor;
import com.animelabs.memorygame.network.models.ImageItems;
import com.animelabs.memorygame.views.PlayGameView;

/**
 * Created by asheeshsharma on 01/04/18.
 */

public interface PlayGamePresenter {
    void attach(PlayGameView playGameView, PlayGameInteractor playGameInteractor);
    void fetchItems();
    void destroy();
    void onItemClicked(int totalCount, ImageItems imageItems1, ImageItems imageItems2, int position);
}
