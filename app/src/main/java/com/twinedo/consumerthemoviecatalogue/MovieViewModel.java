package com.twinedo.consumerthemoviecatalogue;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.twinedo.consumerthemoviecatalogue.api.MovieService;
import com.twinedo.consumerthemoviecatalogue.api.RestAPI;
import com.twinedo.consumerthemoviecatalogue.object.Credits;
import com.twinedo.consumerthemoviecatalogue.object.CreditsResult;
import com.twinedo.consumerthemoviecatalogue.object.Genres;
import com.twinedo.consumerthemoviecatalogue.object.GenresResult;
import com.twinedo.consumerthemoviecatalogue.object.Movie;
import com.twinedo.consumerthemoviecatalogue.object.MovieResult;
import com.twinedo.consumerthemoviecatalogue.object.Trailer;
import com.twinedo.consumerthemoviecatalogue.object.TrailersResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.twinedo.consumerthemoviecatalogue.BuildConfig.API_KEY;

public class MovieViewModel extends ViewModel {

    // private static final String TAG = MovieViewModel.class.getSimpleName();

    private MutableLiveData<ArrayList<Movie>> movies;
    private MutableLiveData<ArrayList<Trailer>> trailers;
    private MutableLiveData<ArrayList<Credits>> credits;
    private MutableLiveData<ArrayList<Genres>> genres;

    private ArrayList<Movie> listMovies = new ArrayList<>();
    private ArrayList<Trailer> listTrailers = new ArrayList<>();
    private ArrayList<Credits> listCredits = new ArrayList<>();
    private ArrayList<Genres> listGenres = new ArrayList<>();

    public MutableLiveData<ArrayList<Movie>> getMovies() {
        if (movies == null ) {
            movies = new MutableLiveData<>();
            setMovies();
        }
        return movies;
    }

    public MutableLiveData<ArrayList<Trailer>> getTrailers(long id) {
        if (trailers == null) {
            trailers = new MutableLiveData<>();
            setTrailers(id);
        }
        return trailers;
    }

    public MutableLiveData<ArrayList<Credits>> getCredits(long id) {
        if (credits == null) {
            credits = new MutableLiveData<>();
            setCredits(id);

        }
        return credits;
    }

    public MutableLiveData<ArrayList<Genres>> getGenres(long id) {
        if (genres == null) {
            genres = new MutableLiveData<>();
            setGenres(id);
        }
        return genres;
    }

    private void setMovies() {
        final MovieService movieService = RestAPI.getRetrofit().create(MovieService.class);
        Call<MovieResult> call = movieService.getMovies(API_KEY);
        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(@NonNull Call<MovieResult> call, @NonNull Response<MovieResult> response) {
                if (response.code() == 200 && response.isSuccessful() && response.body() != null){
                    listMovies = response.body().results;
                    movies.postValue(listMovies);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResult> call, @NonNull Throwable t) {
                movies.postValue(null);
            }
        });
    }

    private void setTrailers(long id) {
        final MovieService movieService = RestAPI.getRetrofit().create(MovieService.class);
        Call<TrailersResult> call = movieService.getTrailerMovie(id, API_KEY);
        call.enqueue(new Callback<TrailersResult>() {
            @Override
            public void onResponse(@NonNull Call<TrailersResult> call, @NonNull Response<TrailersResult> response) {

                if (response.code() == 200 && response.isSuccessful() && response.body() != null) {
                    listTrailers = response.body().results;
                    trailers.postValue(listTrailers);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrailersResult> call, @NonNull Throwable t) {
                trailers.postValue(null);
            }
        });
    }

    private void setCredits(long id) {
        final MovieService movieService = RestAPI.getRetrofit().create(MovieService.class);
        Call<CreditsResult> call = movieService.getCreditsMovie(id, API_KEY);
        call.enqueue(new Callback<CreditsResult>() {
            @Override
            public void onResponse(@NonNull Call<CreditsResult> call, @NonNull Response<CreditsResult> response) {

                if (response.code() == 200 && response.isSuccessful() && response.body() != null) {
                    listCredits = response.body().cast;
                    credits.postValue(listCredits);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CreditsResult> call, @NonNull Throwable t) {
                credits.postValue(null);
            }
        });
    }

    private void setGenres(long id) {
        final MovieService movieService = RestAPI.getRetrofit().create(MovieService.class);
        Call<GenresResult> call = movieService.getGenresMovie(id, API_KEY);
        call.enqueue(new Callback<GenresResult>() {
            @Override
            public void onResponse(@NonNull Call<GenresResult> call, @NonNull Response<GenresResult> response) {

                if (response.code() == 200 && response.isSuccessful() && response.body() != null) {
                    listGenres = response.body().genres;
                    genres.postValue(listGenres);
                }
            }

            @Override
            public void onFailure(@NonNull Call<GenresResult> call, @NonNull Throwable t) {
                genres.postValue(null);
            }
        });
    }

}
