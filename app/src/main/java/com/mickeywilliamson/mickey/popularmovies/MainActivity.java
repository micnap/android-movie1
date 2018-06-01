package com.mickeywilliamson.mickey.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private TextView mErrorMessage;
    private ProgressBar mLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_list);
        mErrorMessage = (TextView) findViewById(R.id.tv_error_message);
        mLoader = (ProgressBar) findViewById(R.id.pb_loader);

        GridLayoutManager lm = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(lm);

        mMovieAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mMovieAdapter);

        loadMovies();



    }

    private void loadMovies() {
        mErrorMessage.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);

        new MovieAsyncTask().execute(MovieGlobals.SORT != null ? MovieGlobals.SORT : MovieAdapter.SORT_POPULAR);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onListItemClick(Movie clickedMovie) {

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("Movie", clickedMovie);
        startActivity(intent);
    }


    public class MovieAsyncTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoader.setVisibility(View.VISIBLE);
        }


        @Override
        protected ArrayList<Movie> doInBackground(String... strings) {

            ArrayList<Movie> movies = null;
            String sort = strings[0];

            URL url = NetworkUtils.buildUrl(MainActivity.this, sort);

            try {
                String jsonResponse = NetworkUtils.getResponseFromHttpUrl(url);

                movies = JsonUtils.parseMoviesFromJSON(jsonResponse);

                return movies;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            mLoader.setVisibility(View.INVISIBLE);
            if (movies != null) {
                mErrorMessage.setVisibility(View.INVISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mMovieAdapter.setMovieData(movies);
            } else {
                showErrorMessage();
            }

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sort_popular) {
            MovieGlobals.SORT = MovieAdapter.SORT_POPULAR;
            new MovieAsyncTask().execute(MovieAdapter.SORT_POPULAR);
            return true;
        }

        // COMPLETED (2) Launch the map when the map menu item is clicked
        if (id == R.id.sort_top_rated) {
            MovieGlobals.SORT = MovieAdapter.SORT_TOP_RATED;
            new MovieAsyncTask().execute(MovieAdapter.SORT_TOP_RATED);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
