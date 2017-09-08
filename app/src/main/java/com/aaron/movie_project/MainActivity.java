package com.aaron.movie_project;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.aaron.movie_project.model.Movie.MovieProfile;
import com.aaron.movie_project.model.Movie.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MovieProfile movieProfile;
    EditText etMovieName;
    ResultListAdaptor resultListAdaptor;
    RecyclerView resultRecycleView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMovieName = (EditText)findViewById(R.id.etMovieName);
    }
    public void processSearch(View view) {
        String movieName = etMovieName.getText().toString();
        Thread movieInfoTread = new Thread(initMovieInfo(movieName));
        movieInfoTread.start();
        try {
            movieInfoTread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initRecycleView(movieProfile.getResults());
    }


    public void initRecycleView(List<Result> passedResultsList) {
        resultListAdaptor = new ResultListAdaptor(passedResultsList,getApplicationContext());
        resultRecycleView = (RecyclerView)findViewById(R.id.rvResultLIst);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        resultRecycleView.setLayoutManager(layoutManager);
        resultRecycleView.setItemAnimator(itemAnimator);
        resultRecycleView.setAdapter(resultListAdaptor);

    }



    Runnable initMovieInfo(final String movieName){
        final MovieInfoHandler movieInfoHandler = new MovieInfoHandler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    movieProfile = movieInfoHandler.generateMovieProfile(movieName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        return runnable;
    }


}
