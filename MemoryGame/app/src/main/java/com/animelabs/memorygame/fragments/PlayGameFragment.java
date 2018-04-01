package com.animelabs.memorygame.fragments;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.animelabs.memorygame.R;
import com.animelabs.memorygame.activities.ContainerActivity;
import com.animelabs.memorygame.adapters.GridAdapter;
import com.animelabs.memorygame.interactors.PlayGameInteractorImpl;
import com.animelabs.memorygame.network.models.ImageItems;
import com.animelabs.memorygame.presenters.PlayGamePresenter;
import com.animelabs.memorygame.presenters.PlayGamePresenterImpl;
import com.animelabs.memorygame.views.PlayGameView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by asheeshsharma on 01/04/18.
 * PlayGameFragment
 */

public class PlayGameFragment extends Fragment implements PlayGameView, GridAdapter.ItemClickListener {
    private ProgressDialog progressDialg;
    private GridAdapter gridAdapter;
    private PlayGamePresenter presenter;
    private ImageItems mImageItem;
    private int totalCount = 0;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.retry_button)
    Button mRefresh;
    @BindView(R.id.textView)
    TextView mTimerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PlayGamePresenterImpl();
        presenter.attach(this, new PlayGameInteractorImpl());
        presenter.fetchItems();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play_game_fragment, container, false);
        ButterKnife.bind(this, view);
        setUpViews();
        return view;
    }

    @OnClick(R.id.retry_button)
    public void onHitClick(View view) {
        Glide.with(getContext()).load(R.mipmap.ic_launcher).into(mImageView);
        presenter.fetchItems();
        mRefresh.setVisibility(View.GONE);
        totalCount = 0;
    }

    public void setUpViews() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    @Override
    public void showProgress() {
        progressDialg = new ProgressDialog(getContext());
        progressDialg.setTitle("Loading");
        progressDialg.setMessage("Wait while loading...");
        progressDialg.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progressDialg.show();
    }

    @Override
    public void hideProgress() {
        if (progressDialg != null && progressDialg.isShowing()) {
            progressDialg.dismiss();
        }
    }

    @Override
    public void setData(final List<ImageItems> imageItems) {
        gridAdapter = new GridAdapter(getContext(), imageItems);
        gridAdapter.setShowHide(true);
        gridAdapter.setClickListener(this);
        mRecyclerView.setAdapter(gridAdapter);
        mRefresh.setVisibility(View.GONE);
        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new CountDownTimer(10000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                mTimerView.setText(millisUntilFinished / 1000 + "");
                            }

                            public void onFinish() {
                                mTimerView.setText("Time Over");
                                gridAdapter.setShowHide(false);
                                gridAdapter.notifyDataSetChanged();
                                mImageItem = getRandomImageItem(imageItems);
                                Glide.with(getContext()).load(mImageItem.getMedia().getM()).apply(new RequestOptions().placeholder(R.mipmap.ic_launcher)).into(mImageView);
                            }
                        }.start();
                    }
                }, 1500);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mRecyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    @Override
    public void showMessage(int id) {
        if (getView() != null) {
            Snackbar snackbar = Snackbar
                    .make(getView(), getString(id), Snackbar.LENGTH_SHORT);
            snackbar.show();
        } else {
            Toast.makeText(getContext(), getString(id), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(String msg) {
        if (getView() != null) {
            Snackbar snackbar = Snackbar
                    .make(getView(), msg, Snackbar.LENGTH_SHORT);
            snackbar.show();
        } else {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onWinGame(int position) {
        gridAdapter.setSelectedPosition(position);
        gridAdapter.notifyDataSetChanged();
        mRefresh.setVisibility(View.VISIBLE);
        totalCount = 2;
    }

    @Override
    public void onLostGame(int position) {
        gridAdapter.setSelectedPosition(position);
        gridAdapter.notifyDataSetChanged();
        if (totalCount == 1) {
            mRefresh.setVisibility(View.VISIBLE);
            mRefresh.setEnabled(true);
        }
    }

    @Override
    public void onItemClick(int position, ImageItems data) {
        /*if (totalCount < 2) {
            if (mImageItem.getMedia().getM().contentEquals(data.getMedia().getM())) {
                if (getView() != null) {
                    Snackbar snackbar = Snackbar
                            .make(getView(), "You Win! Refresh to try again!", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    Toast.makeText(getContext(), "You Win! Refresh to try again!", Toast.LENGTH_SHORT).show();
                }
                gridAdapter.setSelectedPosition(position);
                gridAdapter.notifyDataSetChanged();
                mRefresh.setVisibility(View.VISIBLE);
                totalCount = 2;
            } else {
                gridAdapter.setSelectedPosition(position);
                gridAdapter.notifyDataSetChanged();
                String text = "";
                if (totalCount == 0) {
                    text = "Uh Oh! Last Chance";

                } else {
                    mRefresh.setVisibility(View.VISIBLE);
                    mRefresh.setEnabled(true);
                    text = "Please Hit Refresh to Play Again!";
                }
                if (getView() != null) {
                    Snackbar snackbar = Snackbar
                            .make(getView(), text, Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                }
            }
        }*/
        if (mTimerView.getText().toString().contentEquals("Time Over")) {
            presenter.onItemClicked(totalCount, mImageItem, data, position);
            totalCount = totalCount + 1;
        }
    }

    public static ImageItems getRandomImageItem(List<ImageItems> items) {
        try {
            return items.get((new Random()).nextInt(items.size()));
        } catch (Throwable e) {
            return null;
        }
    }
}
