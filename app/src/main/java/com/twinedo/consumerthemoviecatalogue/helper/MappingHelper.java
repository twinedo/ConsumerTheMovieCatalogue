package com.twinedo.consumerthemoviecatalogue.helper;

import android.database.Cursor;

import com.twinedo.consumerthemoviecatalogue.object.Movie;
import com.twinedo.consumerthemoviecatalogue.object.TvShows;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.twinedo.consumerthemoviecatalogue.db.DatabaseContract.FavoriteColumns.BACKDROP;
import static com.twinedo.consumerthemoviecatalogue.db.DatabaseContract.FavoriteColumns.OVERVIEW;
import static com.twinedo.consumerthemoviecatalogue.db.DatabaseContract.FavoriteColumns.POSTER;
import static com.twinedo.consumerthemoviecatalogue.db.DatabaseContract.FavoriteColumns.RELEASE;
import static com.twinedo.consumerthemoviecatalogue.db.DatabaseContract.FavoriteColumns.TITLE;
import static com.twinedo.consumerthemoviecatalogue.db.DatabaseContract.FavoriteColumns.VOTE;

public class MappingHelper {

    public static ArrayList<Movie> mapCursorMovieToArrayList(Cursor movieCursor) {
        ArrayList<Movie> movieArrayList = new ArrayList<>();

        while (movieCursor.moveToNext()) {
            int id = movieCursor.getInt(movieCursor.getColumnIndexOrThrow(_ID));
            String title = movieCursor.getString(movieCursor.getColumnIndexOrThrow(TITLE));
            String poster_path = movieCursor.getString(movieCursor.getColumnIndexOrThrow(POSTER));
            String backdrop_path = movieCursor.getString(movieCursor.getColumnIndexOrThrow(BACKDROP));
            String release_date = movieCursor.getString(movieCursor.getColumnIndexOrThrow(RELEASE));
            Double vote_average = movieCursor.getDouble(movieCursor.getColumnIndexOrThrow(VOTE));
            String overview = movieCursor.getString(movieCursor.getColumnIndexOrThrow(OVERVIEW));

            movieArrayList.add(new Movie(id, poster_path, backdrop_path, title, release_date, overview, vote_average));
        }

        return movieArrayList;
    }

    public static ArrayList<TvShows> mapCursorTVToArrayList(Cursor tvCursor) {
        ArrayList<TvShows> tvShowsArrayList = new ArrayList<>();

        while (tvCursor.moveToNext()) {
            int id = tvCursor.getInt(tvCursor.getColumnIndexOrThrow(_ID));
            String name = tvCursor.getString(tvCursor.getColumnIndexOrThrow(TITLE));
            String poster_path = tvCursor.getString(tvCursor.getColumnIndexOrThrow(POSTER));
            String backdrop_path = tvCursor.getString(tvCursor.getColumnIndexOrThrow(BACKDROP));
            String first_air_date = tvCursor.getString(tvCursor.getColumnIndexOrThrow(RELEASE));
            Double vote_average = tvCursor.getDouble(tvCursor.getColumnIndexOrThrow(VOTE));
            String overview = tvCursor.getString(tvCursor.getColumnIndexOrThrow(OVERVIEW));

            tvShowsArrayList.add(new TvShows(id, poster_path, backdrop_path, name, first_air_date, overview, vote_average));
        }

        return tvShowsArrayList;
    }

}
