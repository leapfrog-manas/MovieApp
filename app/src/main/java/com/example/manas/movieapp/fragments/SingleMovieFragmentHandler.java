package com.example.manas.movieapp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.manas.movieapp.API;
import com.example.manas.movieapp.Adapters.CastAdapter;
import com.example.manas.movieapp.Info.SinlgeMovie;
import com.example.manas.movieapp.R;
import com.example.manas.movieapp.Utilities.DatabaseHelper;
import com.melnykov.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Manas on 3/23/2015.
 */
public class SingleMovieFragmentHandler extends ActionBarActivity {
    String id;

SmoothProgressBar smoothProgressBar;
    TextView nameTV, synopsisTV, budgetTV, languageTV,revenueTV;
    ImageView backdropIV,posterIV;
    RatingBar ratingRB;
    ScrollView scrollView;
    android.support.v7.widget.Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    DatabaseHelper db;
    ListView cast;
    CastAdapter castAdater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_movie);
        db = new DatabaseHelper(SingleMovieFragmentHandler.this);

        Bundle b = getIntent().getExtras();

        id = b.getString("id");
        Log.e("ID", id);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.appbar_single_movie);
        ratingRB = (RatingBar) findViewById(R.id.singleMovieRating);
        backdropIV = (ImageView) findViewById(R.id.backdropiv);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        nameTV = (TextView) findViewById(R.id.nameTV);
        synopsisTV = (TextView) findViewById(R.id.synopsisTV);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        posterIV = (ImageView) findViewById(R.id.posterIV);
        languageTV = (TextView) findViewById(R.id.languageTV);
        revenueTV = (TextView) findViewById(R.id.revenueTV);
        budgetTV = (TextView) findViewById(R.id.budgetTV);
        cast = (ListView) findViewById(R.id.castLV);
        smoothProgressBar =(SmoothProgressBar) findViewById(R.id.smoothProgressBar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(8);
//
//        setActionBarColor();

        requestData();
//
//
//
        backdropIV.setImageResource(R.drawable.thetourist);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {

            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY();
                final int headerHeight = findViewById(R.id.backdropiv).getHeight() - 100;
                final float ratio = (float) Math.min(Math.max(scrollY, 0), headerHeight) / headerHeight;
                final int newAlpha = (int) (ratio * 255);
                toolbar.getBackground().setAlpha(newAlpha);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (db.getMovieStatus(name) == 1) {
//
//                    db.updateMovieStatus(name, 0);
//                    floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.favoriteiconsmall));
//                } else if (db.getMovieStatus(name) == 0) {
//                    db.updateMovieStatus(name, 1);
//                    floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.favoriteiconyellow));
//                }


            }
        });
    }

//    private void setActionBarColor() {
//        //change colors of the stars in RatingBar
//        LayerDrawable stars = (LayerDrawable) ratingRB.getProgressDrawable();
//        stars.getDrawable(0).setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
//        stars.getDrawable(1).setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
//        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
//
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), poster_id);
//        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//
//                int darkVibrantColor = palette.getDarkVibrantColor(222345);
//                int lightVibrantColor = palette.getLightVibrantColor(222345);
//                int darkMutedColor = palette.getDarkMutedColor(222345);
//                int lightMutedColor = palette.getLightMutedColor(222345);
//                int mutedColor = palette.getMutedColor(222345);
//                int vibrantColor = palette.getVibrantColor(222345);
//                toolbar.setBackgroundColor(vibrantColor);
//
//                //change floatingactionbar color according to palette
//                floatingActionButton.setColorPressed(darkVibrantColor);
//                floatingActionButton.setColorNormal(vibrantColor);
//
//
//            }
//        });
//    }

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

    private void requestData() {
        String url = "https://api.themoviedb.org/3";
    ;
        final RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .build();
        API api = adapter.create(API.class);

        api.getSingle(id,new Callback<SinlgeMovie>() {
            @Override
            public void success(SinlgeMovie singleMovie, Response response) {
                Log.e("BackDrop Path", singleMovie.backdrop_path);
                Log.e("Poster Path", singleMovie.poster_path);

                Picasso.with(SingleMovieFragmentHandler.this).load("http://image.tmdb.org/t/p/w500"+singleMovie.backdrop_path).into(backdropIV);
                Picasso.with(SingleMovieFragmentHandler.this).load("http://image.tmdb.org/t/p/w500"+singleMovie.poster_path).into(posterIV, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        posterIV.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    }

                    @Override
                    public void onError() {

                    }
                });

               setEveryThing(singleMovie);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Error",error.toString());
            }
        });

    }

    private void setEveryThing(SinlgeMovie singleMovie){
        scrollView.setVisibility(View.VISIBLE);
        nameTV.setText(singleMovie.title);
        getSupportActionBar().setTitle(singleMovie.title);
        synopsisTV.setText(singleMovie.overview);
        ratingRB.setRating(Float.parseFloat(singleMovie.vote_average) / 2);
        budgetTV.setText(singleMovie.budget);
        languageTV.setText(singleMovie.original_language);
        revenueTV.setText(singleMovie.revenue);
        castAdater = new CastAdapter(getLayoutInflater(),SingleMovieFragmentHandler.this,singleMovie);
        cast.setAdapter(castAdater);
        smoothProgressBar.setVisibility(View.INVISIBLE);
        Log.e("Cast - Name", singleMovie.casts.cast.get(1).character + "  " + singleMovie.casts.cast.get(1).name);
    }



}
