package com.mickeywilliamson.mickey.popularmovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public final class JsonUtils {

    public static ArrayList<Movie> parseMoviesFromJSON(String moviesJsonString) throws JSONException {


        final String FIELD_TITLE = "title";
        final String FIELD_IMAGE = "poster_path";
        final String FIELD_PLOT = "overview";
        final String FIELD_RATING = "vote_average";
        final String FIELD_RELEASE_DATE = "release_date";

        ArrayList<Movie> movies = new ArrayList<Movie>();

        JSONObject moviesJson = new JSONObject(moviesJsonString);

        JSONArray results = moviesJson.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {

            Movie movie = new Movie();
            movie.setTitle(results.getJSONObject(i).getString(FIELD_TITLE));
            movie.setImage(results.getJSONObject(i).getString(FIELD_IMAGE));
            movie.setPlot(results.getJSONObject(i).getString(FIELD_PLOT));
            movie.setRating(results.getJSONObject(i).getString(FIELD_RATING));
            movie.setReleaseDate(results.getJSONObject(i).getString(FIELD_RELEASE_DATE));

            movies.add(movie);
        }

        return movies;
    }


}
