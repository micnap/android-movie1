package com.mickeywilliamson.mickey.popularmovies;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movie_list);

        new MovieAsyncTask().execute();



    }


    class MovieAsyncTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {


            URL url = NetworkUtils.buildUrl(MainActivity.this, "popular");
            Log.d("URL", url.toString());

            try {
                String jsonResponse = NetworkUtils.getResponseFromHttpUrl(url);
                Log.d("JSON", jsonResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
