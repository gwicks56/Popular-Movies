package com.subversivestudio.movie3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Movie Detail");

        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra("sample object");
        TextView movieTitle = (TextView) findViewById(R.id.detail_title);
        movieTitle.setText(movie.getOriginal_title());
        TextView movieReleaseDate = (TextView) findViewById(R.id.detail_release_date);

        movieReleaseDate.setText(movie.getRelease_date());
        TextView movieSynopsis = (TextView) findViewById(R.id.detail_overview);
        movieSynopsis.setText(movie.getOverview());
        TextView voteAverage = (TextView) findViewById(R.id.detail_vote_average);
        voteAverage.setText(movie.getVote_average() + " / 10");

        ImageView poster = (ImageView) findViewById(R.id.detail_image);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w185//" + movie.getPoster_path()).into(poster);





        //Movie movie = (Movie) intent.getSerializableExtra(MOVIE_TRANSFER);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
