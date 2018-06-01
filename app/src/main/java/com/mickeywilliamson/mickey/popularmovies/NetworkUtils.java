/*
 * Derived from https://github.com/udacity/ud851-Exercises/blob/student/Lesson05b-Smarter-GitHub-Repo-Search/T05b.03-Solution-PolishAsyncTask/app/src/main/java/com/example/android/asynctaskloader/utilities/NetworkUtils.java
 */
package com.mickeywilliamson.mickey.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

    final static String BASE_URL = "http://api.themoviedb.org/3/movie/";
    // http://api.themoviedb.org/3/movie/top_rated?api_key=db0b3d5274db83389840b9666d851ad0

    final static String PARAM_API_KEY = "api_key";

    /*
     * The sort field. One of stars, forks, or updated.
     * Default: results are sorted by best match if no field is specified.
     */
    final static String PARAM_SORT_POPULAR = "popular";
    final static String PARAM_SORT_TOP_RATED = "top_rated";

    private static void getKey(Context context) {
        MovieGlobals globals = new MovieGlobals();
    }

    /**
     * Builds the URL used to query Github.
     *
     * @return The URL to use to query the weather server.
     */
    public static URL buildUrl(Context context, String sort) {

        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(sort)
                .appendQueryParameter(PARAM_API_KEY, MovieGlobals.getKey(context))
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}