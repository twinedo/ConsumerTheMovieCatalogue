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
import com.twinedo.consumerthemoviecatalogue.object.Trailer;
import com.twinedo.consumerthemoviecatalogue.object.TrailersResult;
import com.twinedo.consumerthemoviecatalogue.object.TvShows;
import com.twinedo.consumerthemoviecatalogue.object.TvShowsResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.twinedo.consumerthemoviecatalogue.BuildConfig.API_KEY;

public class TvViewModel extends ViewModel {

    // private static final String TAG = TvViewModel.class.getSimpleName();

    private MutableLiveData<ArrayList<TvShows>> tvShows;
    private MutableLiveData<ArrayList<Trailer>> trailers;
    private MutableLiveData<ArrayList<Credits>> credits;
    private MutableLiveData<ArrayList<Genres>> genres;

    private ArrayList<TvShows> listTv = new ArrayList<>();
    private ArrayList<Trailer> listTrailers = new ArrayList<>();
    private ArrayList<Credits> listCredits = new ArrayList<>();
    private ArrayList<Genres> listGenres = new ArrayList<>();

    public MutableLiveData<ArrayList<TvShows>> getTvShows() {
        if (tvShows == null ) {
            tvShows = new MutableLiveData<>();
            setTvShows();
        }
        return tvShows;
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

    private void setTvShows() {
        MovieService movieService = RestAPI.getRetrofit().create(MovieService.class);
        Call<TvShowsResult> call = movieService.getTVShows(API_KEY);
        call.enqueue(new Callback<TvShowsResult>() {
            @Override
            public void onResponse(@NonNull Call<TvShowsResult> call, @NonNull Response<TvShowsResult> response) {
                if (response.code() == 200 && response.isSuccessful() && response.body() != null){
                    listTv = response.body().results;
                    tvShows.postValue(listTv);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvShowsResult> call, @NonNull Throwable t) {
                tvShows.postValue(null);
            }
        });
    }

    private void setTrailers(long id) {
        final MovieService movieService = RestAPI.getRetrofit().create(MovieService.class);
        Call<TrailersResult> call = movieService.getTrailerTV(id, API_KEY);
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
        Call<CreditsResult> call = movieService.getCreditsTV(id, API_KEY);
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
                trailers.postValue(null);
            }
        });
    }

    private void setGenres(long id) {
        final MovieService movieService = RestAPI.getRetrofit().create(MovieService.class);
        Call<GenresResult> call = movieService.getGenresTV(id, API_KEY);
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
