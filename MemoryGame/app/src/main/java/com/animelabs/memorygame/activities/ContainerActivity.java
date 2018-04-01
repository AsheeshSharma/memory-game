package com.animelabs.memorygame.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.animelabs.memorygame.R;
import com.animelabs.memorygame.fragments.PlayGameFragment;
import com.animelabs.memorygame.fragments.StartGameFragment;

public class ContainerActivity extends AppCompatActivity {
    private static final String TAG_FRAGMENT_START = "tag_fragment";
    public static final String TAG_FRAGMENT_PLAY = "play_fragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        PlayGameFragment playFragment = (PlayGameFragment) fm.findFragmentByTag(TAG_FRAGMENT_PLAY);
        if (playFragment == null) {
            replaceFragment(new StartGameFragment(), TAG_FRAGMENT_START);
        }

    }

    public void replaceFragment(Fragment fragment, String tag) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
            ft.replace(R.id.flContainer, fragment, tag);                                // add    Fragment
            ft.commit();
        }
    }

}
