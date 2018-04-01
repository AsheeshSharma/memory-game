package com.animelabs.memorygame.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.animelabs.memorygame.R;
import com.animelabs.memorygame.network.models.ImageItems;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by asheeshsharma on 01/04/18.
 * GridAdapter
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private List<ImageItems> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private boolean mShowHide;
    private int selectedPosition = -1;

    public GridAdapter(Context context, List<ImageItems> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    public void setData(List<ImageItems> data) {
        this.mData.clear();
        this.mData.addAll(data);
        this.notifyDataSetChanged();
    }

    public void setShowHide(boolean showHide) {
        mShowHide = showHide;
    }

    public void setSelectedPosition(int position) {
        if(position > 0) {
            selectedPosition = position;
        }
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.image_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageItems imageItems = mData.get(position);
        if (mInflater.getContext() != null) {
            if (mShowHide || (selectedPosition != -1 && selectedPosition == position)) {
                Glide.with(mInflater.getContext())
                        .load(imageItems.getMedia().getM()).apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher)
                        .fitCenter())
                        .into(holder.imageView);
            } else {
                Glide.with(mInflater.getContext())
                        .load(R.mipmap.ic_launcher).apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher)
                        .fitCenter())
                        .into(holder.imageView);
            }
        }


    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.info_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(getAdapterPosition(), mData.get(getAdapterPosition()));
        }
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(int position, ImageItems data);
    }
}