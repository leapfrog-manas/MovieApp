package com.example.manas.movieapp.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.manas.movieapp.Info.MostPopular;
import com.example.manas.movieapp.MainActivity;
import com.example.manas.movieapp.Utilities.DatabaseHelper;
import com.example.manas.movieapp.fragments.MainFragmentHandler;
import com.example.manas.movieapp.fragments.SingleMovieFragmentHandler;
import com.example.manas.movieapp.Info.MovieInfo;
import com.example.manas.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Manas on 3/23/2015.
 */
public class HomeFragmentRCVAdapter extends RecyclerView.Adapter<HomeFragmentRCVAdapter.ViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    MainActivity mainActivity;
    MostPopular mostPopular = new MostPopular();

    public HomeFragmentRCVAdapter(Context c) {
        this.context = c;
        layoutInflater = layoutInflater.from(context);

        this.mainActivity = (MainActivity) c;
    }

    public void setMostPopularObject(MostPopular mostPopular) {
        this.mostPopular = mostPopular;
        notifyDataSetChanged();
    }

    @Override
    public HomeFragmentRCVAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = layoutInflater.inflate(R.layout.card_view_for_recycler_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final HomeFragmentRCVAdapter.ViewHolder holder, int i) {
        Picasso.with(context).load("http://image.tmdb.org/t/p/w500" + mostPopular.results.get(i).backdrop_path).into(holder.moviePoster);
        holder.movieName.setText(mostPopular.results.get(i).title);
        holder.id = mostPopular.results.get(i).id;
        holder.movieRating.setRating(Float.parseFloat(mostPopular.results.get(i).vote_average) / 2);
        DatabaseHelper db = new DatabaseHelper(context);
        db.getWritableDatabase();
    }

    @Override
    public int getItemCount() {
        return mostPopular.results.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView moviePoster;
        TextView movieName;
        String id;

        RatingBar movieRating;
        ViewGroup viewGroup;
        ImageView bookmark;
        RelativeLayout moviePosterBottom;

        public ViewHolder(View itemView) {
            super(itemView);
            viewGroup = (ViewGroup) itemView.findViewById(R.id.container_a);
            bookmark = (ImageView) itemView.findViewById(R.id.bookmarkIV);
            moviePoster = (ImageView) itemView.findViewById(R.id.moviePoster);
            movieName = (TextView) itemView.findViewById(R.id.movieName);
            movieRating = (RatingBar) itemView.findViewById(R.id.movieRating);
            moviePosterBottom = (RelativeLayout) itemView.findViewById(R.id.moviePosterBottom);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Bundle bundle = new Bundle();
            bundle.putString("id", id);

            mainActivity.startSingleMovieViewActivity(bundle, viewGroup);

        }


    }


}
