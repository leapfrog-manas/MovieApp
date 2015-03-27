package com.example.manas.movieapp.fragments;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.manas.movieapp.R;

/**
 * Created by Manas on 3/23/2015.
 */
public class SingleMovieFragmentHandler extends ActionBarActivity {
    String name;
    String rating;
    String synopsis;
    int poster_id;
    TextView nameTV, synopsisTV;
    ImageView posterIV;
    RatingBar ratingRB;
    ScrollView scrollView;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_movie);


        Bundle b = getIntent().getExtras();
        poster_id = b.getInt("poster_id");
        name = b.getString("name");
        rating = b.getString("rating");
        synopsis = b.getString("synopsis");

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.appbar_single_movie);
        ratingRB = (RatingBar) findViewById(R.id.singleMovieRating);
        posterIV = (ImageView) findViewById(R.id.posteriv);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        nameTV = (TextView) findViewById(R.id.nameTV);
        synopsisTV = (TextView) findViewById(R.id.synopsisTV);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(8);
        getSupportActionBar().setTitle(name);

        ratingRB.setRating(Float.parseFloat(rating) / 2);
        synopsisTV.setText(synopsis);
        nameTV.setText(name);
        posterIV.setImageResource(poster_id);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {

            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY();
                final int headerHeight = findViewById(R.id.posteriv).getHeight() - 100;
                final float ratio = (float) Math.min(Math.max(scrollY, 0), headerHeight) / headerHeight;
                final int newAlpha = (int) (ratio * 255);
                toolbar.getBackground().setAlpha(newAlpha);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
