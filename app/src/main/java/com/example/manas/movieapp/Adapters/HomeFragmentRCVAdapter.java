package com.example.manas.movieapp.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.manas.movieapp.Fragment_Handlers.MainFragmentHandler;
import com.example.manas.movieapp.Fragment_Handlers.SingleMovieFragmentHandler;
import com.example.manas.movieapp.Info.MovieInfo;
import com.example.manas.movieapp.MainActivity;
import com.example.manas.movieapp.R;

import java.util.List;

import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by Manas on 3/23/2015.
 */
public class HomeFragmentRCVAdapter extends RecyclerView.Adapter<HomeFragmentRCVAdapter.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    FragmentManager fragmentManager;
    List<MovieInfo> movieInfoList;
    ViewGroup mRoot;


    public HomeFragmentRCVAdapter(Context c, FragmentManager fragmentManager, List<MovieInfo> movieInfoList,ViewGroup mRoot) {
        this.context = c;
        layoutInflater = layoutInflater.from(context);
        this.fragmentManager = fragmentManager;
        this.movieInfoList = movieInfoList;
        this.mRoot = mRoot;

    }

    @Override
    public HomeFragmentRCVAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = layoutInflater.inflate(R.layout.card_view_for_recycler_view, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(HomeFragmentRCVAdapter.ViewHolder holder, int i) {
        MovieInfo mf = movieInfoList.get(i);
        holder.moviePoster.setImageResource(mf.poster_id);
        holder.movieName.setText(mf.Name);
        holder.movieRating.setRating(Float.parseFloat(mf.rating) / 2);

        holder.name = mf.Name;
        holder.rating = mf.rating;
        holder.poster_id = mf.poster_id;
        holder.synopsis = mf.synopsis;
    }

    @Override
    public int getItemCount() {
        return movieInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView moviePoster;
        TextView movieName;
        String name;
        String rating;
        String synopsis;
        int poster_id;
        RatingBar movieRating;

        public ViewHolder(View itemView) {
            super(itemView);
            moviePoster = (ImageView) itemView.findViewById(R.id.moviePoster);
            movieName = (TextView) itemView.findViewById(R.id.movieName);
            movieRating = (RatingBar) itemView.findViewById(R.id.movieRating);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("rating", rating);
            bundle.putString("synopsis", synopsis);
            bundle.putInt("poster_id", poster_id);

            SingleMovieFragmentHandler singleMovieFragmentHandler = new SingleMovieFragmentHandler(bundle, fragmentManager);
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,0,android.R.anim.slide_out_right,0);

            fragmentTransaction.add(R.id.fragment_for_homepage, singleMovieFragmentHandler);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();
        }

        public void toggleviews(View... views){
            for(View current:views){
                if(current.getVisibility()==View.VISIBLE){
                    current.setVisibility(View.INVISIBLE);
                }
            }

        }
    }


}
