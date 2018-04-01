package com.animelabs.memorygame.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.animelabs.memorygame.R;
import com.animelabs.memorygame.fragments.StartGameFragment;
import com.animelabs.memorygame.network.ApiClient;
import com.animelabs.memorygame.network.ApiInterface;
import com.animelabs.memorygame.network.models.ApiResponse;
import com.animelabs.memorygame.network.models.ImageItems;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContainerActivity extends AppCompatActivity {
    private static final String TAG_FRAGMENT = "tag_fragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new StartGameFragment());
    }

    public void replaceFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
            ft.replace(R.id.flContainer, fragment, TAG_FRAGMENT);                                // add    Fragment
            ft.commit();
        }
    }
}
