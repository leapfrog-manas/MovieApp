package com.example.manas.movieapp;

import com.example.manas.movieapp.Info.MostPopular;
import com.example.manas.movieapp.Info.SinlgeMovie;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Manas on 3/28/2015.
 */
public interface API {
    @GET("/discover/movie?sort_by=popularity.desc&api_key=602db3c7d00ed71f4002c877f7f89cab&page=1")
    void getPopular(retrofit.Callback<MostPopular> callback);

    @GET("/movie/{id}?api_key=602db3c7d00ed71f4002c877f7f89cab&append_to_response=releases,trailers,reviews,casts")
    void getSingle(@Path("id") String id,retrofit.Callback<SinlgeMovie> callback);
}