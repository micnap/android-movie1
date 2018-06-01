package com.mickeywilliamson.mickey.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private ArrayList<Movie> movies;
    private final ListItemClickListener mOnClickListener;

    private static final String IMAGE_BASE_PATH = "http://image.tmdb.org/t/p/";

    public static final String SORT_POPULAR = "popular";
    public static final String SORT_TOP_RATED = "top_rated";

    public MovieAdapter(ListItemClickListener listener) {
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutId = R.layout.movie_list;
        View view = inflater.inflate(layoutId, parent, false);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, int position) {

        final Movie currentMovie = movies.get(position);

        String image_path = getImagePath(currentMovie.getImage(), "w500");
        Picasso.with(holder.mImage.getContext()).load(image_path).into(holder.mImage);

    }

    @Override
    public int getItemCount() {
        if (movies == null) {
            return 0;
        } else {

            return movies.size();
        }

    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mImage;

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.iv_image);
            mImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(movies.get(clickedPosition));
        }
    }

    public void setMovieData(ArrayList<Movie> movieList) {
        movies = movieList;
        notifyDataSetChanged();
    }

    public interface ListItemClickListener {
        void onListItemClick(Movie clickedMovie);
    }

    /**
     *
     * @param fileName
     * @param width
     * // Choices are "w92", "w154", "w185", "w342", "w500", "w780", or "original"
     * @return
     */
    public static String getImagePath(String fileName, String width) {
        return IMAGE_BASE_PATH + width + fileName;
    }

    public static String getYear(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = sdf.parse(dateString);
            Calendar cal = Calendar.getInstance();
            cal.setTime(parsedDate);

            return String.valueOf(cal.get(Calendar.YEAR));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
