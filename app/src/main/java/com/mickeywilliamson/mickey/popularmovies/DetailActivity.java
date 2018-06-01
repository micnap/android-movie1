package com.mickeywilliamson.mickey.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Displays details of a single movie.
 */
public class DetailActivity extends AppCompatActivity {

    TextView mTitle;
    ImageView mImage;
    TextView mPlot;
    TextView mRating;
    TextView mReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTitle = (TextView) findViewById(R.id.tv_title);
        mImage = (ImageView) findViewById(R.id.tv_image);
        mPlot = (TextView) findViewById(R.id.tv_plot);
        mRating = (TextView) findViewById(R.id.tv_rating);
        mReleaseDate = (TextView) findViewById(R.id.tv_release_date);

        // Get the movie data passed in from the Main screen and display it.
        Intent intent = getIntent();
        if (intent.hasExtra("movie")) {
            Movie movie = intent.getParcelableExtra("movie");
            mTitle.setText(movie.getTitle());
            Picasso.with(this).load(MovieAdapter.getImagePath(movie.getImage(), MovieAdapter.WIDTH_W342)).into(mImage);
            mImage.setContentDescription(movie.getTitle());
            mPlot.setText(movie.getPlot());
            mRating.setText(movie.getRating() + "/10");
            mReleaseDate.setText(MovieAdapter.getYear(movie.getReleaseDate()));
        }
    }
}
