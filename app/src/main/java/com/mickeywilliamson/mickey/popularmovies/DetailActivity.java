package com.mickeywilliamson.mickey.popularmovies;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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

        Intent intent = getIntent();
        if (intent.hasExtra("Movie")) {
            Movie movie = intent.getParcelableExtra("Movie");
            mTitle.setText(movie.getTitle());
            Picasso.with(this).load(MovieAdapter.getImagePath(movie.getImage(), "w342")).into(mImage);
            mPlot.setText(movie.getPlot());
            mRating.setText(movie.getRating() + "/10");
            mReleaseDate.setText(MovieAdapter.getYear(movie.getReleaseDate()));
        }
    }
}
