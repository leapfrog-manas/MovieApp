package com.example.manas.movieapp.Fragment_Handlers;

import android.media.Image;
import android.media.Rating;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.manas.movieapp.R;

/**
 * Created by Manas on 3/23/2015.
 */
public class SingleMovieFragmentHandler extends Fragment {
    String name;
    String rating;
    String synopsis;
    int poster_id;
    TextView nameTV, synopsisTV;
    ImageView posterIV;
    RatingBar ratingRB;
    FragmentManager fragmentManager;

    public SingleMovieFragmentHandler(Bundle b, FragmentManager fragmentManager) {
        this.poster_id = b.getInt("poster_id");
        this.name = b.getString("name");
        this.rating = b.getString("rating");
        this.synopsis = b.getString("synopsis");
        this.fragmentManager = fragmentManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_single_movie, container, false);
        ratingRB = (RatingBar) v.findViewById(R.id.singleMovieRating);

        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    fragmentManager.popBackStack("tag", 0);

                    return true;
                }
                return false;
            }
        });

        nameTV = (TextView) v.findViewById(R.id.nameTV);
        nameTV.setText(name);
        ratingRB.setRating(Float.parseFloat(rating)/2);
        synopsisTV = (TextView) v.findViewById(R.id.synopsisTV);
        synopsisTV.setText(synopsis);
        posterIV = (ImageView) v.findViewById(R.id.posteriv);
        posterIV.setImageResource(poster_id);

        return v;
    }


}
