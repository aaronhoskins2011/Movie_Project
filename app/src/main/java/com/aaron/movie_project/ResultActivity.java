package com.aaron.movie_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.aaron.movie_project.model.Movie.Result;
import com.bumptech.glide.Glide;

import static com.aaron.movie_project.Constants.IMAGE_BASE_URL;
import static com.aaron.movie_project.Constants.SERIALIZABLE_RESULT;

public class ResultActivity extends AppCompatActivity {
    TextView tvName;
    TextView tvReleaseDate;
    ImageView imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(SERIALIZABLE_RESULT);
        Result result = (Result)bundle.getSerializable(SERIALIZABLE_RESULT);


        tvName = (TextView)findViewById(R.id.tvMovieName);
        tvReleaseDate = (TextView)findViewById(R.id.tvReleaseDate);
        imgPoster = (ImageView)findViewById(R.id.imgPoster);
        tvName.setText(result.getTitle().toString());
        tvReleaseDate.setText(result.getReleaseDate().toString()==null ? "UNKNOWN" : result.getReleaseDate().toString());
        String imageURL = IMAGE_BASE_URL + result.getPosterPath();
        Glide.with(getApplicationContext()).load(imageURL).into(imgPoster);


    }
}
