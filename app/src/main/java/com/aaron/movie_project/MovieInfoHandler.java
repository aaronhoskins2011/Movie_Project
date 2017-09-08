package com.aaron.movie_project;


import android.app.DownloadManager;
import android.net.Uri;
import android.util.Log;

import com.aaron.movie_project.model.Movie.MovieProfile;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.aaron.movie_project.Constants.HTTPS_SCHEME;
import static com.aaron.movie_project.Constants.MOVIE_API_KEY;
import static com.aaron.movie_project.Constants.MOVIE_API_OPTION;
import static com.aaron.movie_project.Constants.MOVIE_API_VERSION;
import static com.aaron.movie_project.Constants.MOVIE_BASE_URL;
import static com.aaron.movie_project.Constants.MOVIE_PARAM_API_KEY;
import static com.aaron.movie_project.Constants.MOVIE_PARAM_QUERY;
import static com.aaron.movie_project.Constants.MOVIE_QUERY_ON_MOVIE;
import static com.aaron.movie_project.Constants.TAG_MOVIE_HAND_JSON_GEN;
import static com.aaron.movie_project.Constants.TAG_MOVIE_HAND_URL_GEN;

/**
 * Created by aaron on 9/8/17.
 */

public class MovieInfoHandler {
    public MovieProfile generateMovieProfile(String movieName) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(getJsonResponse(movieName), MovieProfile.class);
    }

    private String getJsonResponse(String movieName) throws IOException {
        String responseReturn;
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(getURLForJsonResponse(movieName))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            responseReturn = response.body().string();
        }
        Log.d(TAG_MOVIE_HAND_JSON_GEN, "getJsonResponse: Json Response Generated for " + responseReturn);
        return responseReturn;
    }

    private String getURLForJsonResponse(String movieName) {
        String returnString;
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme(HTTPS_SCHEME)
                .authority(MOVIE_BASE_URL)
                .appendPath(MOVIE_API_VERSION)
                .appendPath(MOVIE_API_OPTION)
                .appendPath(MOVIE_QUERY_ON_MOVIE)
                .appendQueryParameter(MOVIE_PARAM_QUERY, movieName)
                .appendQueryParameter(MOVIE_PARAM_API_KEY, MOVIE_API_KEY);

        returnString = uriBuilder.build().toString(); //just so we can log it to, otherwise return this
        Log.d(TAG_MOVIE_HAND_URL_GEN, "getURLForJsonResponse: URL = " + returnString);
        return returnString;
    }
}
