package com.mickeywilliamson.mickey.popularmovies;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MovieGlobals extends Application {

    private static String KEY;


    // From https://stackoverflow.com/questions/9544737/read-file-from-assets
    public String getKey(Context context) {

        if (KEY != null) {
            return KEY;
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open("key.txt")));
            KEY = reader.readLine();

        } catch (IOException e) {
            Log.d("EXCEPTION", e.toString());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.d("EXCEPTION", e.toString());
                }
            }
        }
        return KEY;
    }

}
