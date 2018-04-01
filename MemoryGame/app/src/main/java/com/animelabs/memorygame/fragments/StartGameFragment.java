package com.animelabs.memorygame.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.animelabs.memorygame.R;
import com.animelabs.memorygame.activities.ContainerActivity;
import com.animelabs.memorygame.views.StartGameView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by asheeshsharma on 01/04/18.
 * StartGameFragment
 */

public class StartGameFragment extends Fragment implements StartGameView{
    @Override
    public void startGame() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_game_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.start_game)
    public void onButtonClick(View view) {
        if(getActivity() != null && getActivity() instanceof ContainerActivity) {
            ((ContainerActivity) getActivity()).replaceFragment(new PlayGameFragment(), ContainerActivity.TAG_FRAGMENT_PLAY);
        }
    }
}
