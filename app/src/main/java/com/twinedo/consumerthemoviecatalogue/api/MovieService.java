package com.twinedo.consumerthemoviecatalogue.api;

import com.twinedo.consumerthemoviecatalogue.object.CreditsResult;
import com.twinedo.consumerthemoviecatalogue.object.GenresResult;
import com.twinedo.consumerthemoviecatalogue.object.MovieResult;
import com.twinedo.consumerthemoviecatalogue.object.TrailersResult;
import com.twinedo.consumerthemoviecatalogue.object.TvShowsResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    //get discover movie
    @GET("discover/movie")
    Call<MovieResult> getMovies(@Query("api_key") String apiKey);

    //get discover tv
    @GET("discover/tv")
    Call<TvShowsResult> getTVShows(@Query("api_key") String apiKey);

    //get movie
    @GET("movie/{id}")
    Call<GenresResult> getGenresMovie(@Path("id") long id, @Query("api_key") String apiKey);

    //get tv
    @GET("tv/{id}")
    Call<GenresResult> getGenresTV(@Path("id") long id, @Query("api_key") String apiKey);

    //TrailersMovie
    @GET("movie/{id}/videos")
    Call<TrailersResult> getTrailerMovie(@Path("id") long id, @Query("api_key") String apiKey);

    //TrailersTV
    @GET("tv/{id}/videos")
    Call<TrailersResult> getTrailerTV(@Path("id") long id, @Query("api_key") String apiKey);

    //getCreditsMovie
    @GET("movie/{id}/credits")
    Call<CreditsResult> getCreditsMovie(@Path("id") long id, @Query("api_key") String apiKey);

    //getCreditsTV
    @GET("tv/{id}/credits")
    Call<CreditsResult> getCreditsTV(@Path("id") long id, @Query("api_key") String apiKey);

}
