package com.mickeywilliamson.mickey.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ImageAdapterViewHolder> {


    @NonNull
    @Override
    public ImageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutId = R.layout.movie_list;
        View view = inflater.inflate(layoutId, parent, false);
        return new ImageAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapterViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ImageAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView mImage;
        TextView mtitle;

        public ImageAdapterViewHolder(View itemView) {
            super(itemView);

            mImage = (ImageView) itemView.findViewById(R.id.iv_image);
            mtitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
