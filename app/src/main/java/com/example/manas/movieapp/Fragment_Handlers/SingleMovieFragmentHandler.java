package com.example.manas.movieapp.Fragment_Handlers;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.manas.movieapp.R;
import com.example.manas.movieapp.interfaces.ToolbarAlphaChanger;

/**
 * Created by Manas on 3/23/2015.
 */
public class SingleMovieFragmentHandler extends Fragment implements ToolbarAlphaChanger {
    String name;
    String rating;
    String synopsis;
    int poster_id;
    TextView nameTV, synopsisTV;
    ImageView posterIV;
    RatingBar ratingRB;
    FragmentManager fragmentManager;
    ToolbarAlphaChanger toolbarAlphaChanger;
    ScrollView scrollView;
    int checkScroll = 0;
    View v;

    public SingleMovieFragmentHandler(Bundle b, FragmentManager fragmentManager) {
        this.poster_id = b.getInt("poster_id");
        this.name = b.getString("name");
        this.rating = b.getString("rating");
        this.synopsis = b.getString("synopsis");
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        toolbarAlphaChanger = (ToolbarAlphaChanger) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_single_movie, container, false);
        ratingRB = (RatingBar) v.findViewById(R.id.singleMovieRating);
        scrollView = (ScrollView) v.findViewById(R.id.scrollView);

        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    toolbarAlphaChanger.defaultAlpha();
                    fragmentManager.popBackStack("tag", 0);

                    return true;
                }
                return false;
            }
        });

        nameTV = (TextView) v.findViewById(R.id.nameTV);
        nameTV.setText(name);
        ratingRB.setRating(Float.parseFloat(rating) / 2);
        synopsisTV = (TextView) v.findViewById(R.id.synopsisTV);
        synopsisTV.setText(synopsis);
        posterIV = (ImageView) v.findViewById(R.id.posteriv);
        posterIV.setImageResource(poster_id);
        toolbarAlphaChanger.changeAlpha(15);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {

            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY();
                final int headerHeight = v.findViewById(R.id.posteriv).getHeight() - 100;
                final float ratio = (float) Math.min(Math.max(scrollY, 0), headerHeight) / headerHeight;
                final int newAlpha = (int) (ratio * 255);
                toolbarAlphaChanger.changeAlpha(newAlpha);
                Log.e(" Y", "" + "  " + scrollY);
            }
        });

        return v;
    }


    @Override
    public void changeAlpha(int alpha) {

    }

    @Override
    public void defaultAlpha() {

    }
}
