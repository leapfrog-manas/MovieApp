package com.example.manas.movieapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.manas.movieapp.Utilities.DatabaseHelper;
import com.example.manas.movieapp.fragments.MainFragmentHandler;
import com.example.manas.movieapp.fragments.NavigationDrawerFragmentHandler;
import com.example.manas.movieapp.fragments.SingleMovieFragmentHandler;

import java.io.File;



public class MainActivity extends ActionBarActivity {
    DrawerLayout drawerlayout;
    ActionBarDrawerToggle dtoggle;
    Toolbar toolbar;

    String[] name = new String[]{"21 jump street", "CastAway", "Forrest Gump", "Shawshank Redamption", "Whiplash", "The Tourist", "12 Years A Slave"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeEveryThing();

    }

    public void initializeEveryThing() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(8);


        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        dtoggle = new ActionBarDrawerToggle(this, drawerlayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerlayout.setDrawerListener(dtoggle);
        drawerlayout.post(new Runnable() {
            @Override
            public void run() {
                dtoggle.syncState();
            }
        });


        android.support.v4.app.FragmentManager fragmentmanager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
        MainFragmentHandler mainFragmentHandler = new MainFragmentHandler(getSupportFragmentManager());
        fragmenttransaction.replace(R.id.fragment_for_homepage, mainFragmentHandler);
        fragmenttransaction.replace(R.id.fragment_for_drawer, new NavigationDrawerFragmentHandler());
        fragmenttransaction.commit();

        if(doesDatabaseExist(this,"Movie") == false){
            DatabaseHelper db = new DatabaseHelper(this);
            db.getWritableDatabase();
            db.insertMovieName(name);
        }
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


        if (dtoggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtoggle.onConfigurationChanged(newConfig);
    }


    public void startSingleMovieViewActivity(Bundle bundle, ViewGroup V) {
//        Explode fade = new Explode();
//        fade.setDuration(5000);
//        TransitionManager.beginDelayedTransition(V, fade);
//        toggleVisibility(V);
//        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,null);
        Intent intent = new Intent(MainActivity.this, SingleMovieFragmentHandler.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void toggleVisibility(View... v) {
for(View current : v){
    if(current.getVisibility() == View.VISIBLE){
        current.setVisibility(View.INVISIBLE);
    }
}
    };

    /**
     * method checks database existence.
     * @param context
     * @param dbName
     * @return true if database exist
     */

    public static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }



    }




