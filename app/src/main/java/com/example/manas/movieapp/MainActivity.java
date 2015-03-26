package com.example.manas.movieapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.manas.movieapp.Fragment_Handlers.MainFragmentHandler;
import com.example.manas.movieapp.Fragment_Handlers.NavigationDrawerFragmentHandler;
import com.example.manas.movieapp.interfaces.ToolbarAlphaChanger;


public class MainActivity extends ActionBarActivity implements ToolbarAlphaChanger {
    DrawerLayout drawerlayout;
    ActionBarDrawerToggle dtoggle;
    public Toolbar toolbar;



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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
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

    /*
    /@Author Manas Shrestha
    @params Not req
    @returns Void
    This method will be called by the fragment classes to set the alpha of toolbar.
     */
    @Override
    public void changeAlpha(int alpha) {

        toolbar.getBackground().setAlpha(alpha);
    }

    @Override
    public void defaultAlpha() {
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.getBackground().setAlpha(225);
    }


}
