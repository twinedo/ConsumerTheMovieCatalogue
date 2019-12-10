package com.twinedo.consumerthemoviecatalogue.object;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    //initial
    public long id;
    private String poster_path;
    private String backdrop_path;
    private String title;
    private String release_date;
    private String overview;
    private Double vote_average;

    public Movie() { }

    public Movie(long id, String poster_path, String backdrop_path, String title, String release_date, String overview, Double vote_average) {
        this.id = id;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.title = title;
        this.release_date = release_date;
        this.overview = overview;
        this.vote_average = vote_average;
    }

    /*public Movie(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.title = getColumnString(cursor, DatabaseContract.FavoriteColumns.TITLE);
        this.poster_path = getColumnString(cursor, DatabaseContract.FavoriteColumns.POSTER);
        this.backdrop_path = getColumnString(cursor, DatabaseContract.FavoriteColumns.BACKDROP);
        this.release_date = getColumnString(cursor, DatabaseContract.FavoriteColumns.RELEASE);
        this.vote_average = getColumnDouble(cursor, DatabaseContract.FavoriteColumns.VOTE);
        this.overview = getColumnString(cursor, DatabaseContract.FavoriteColumns.OVERVIEW);
    }*/

    //Getter
    public long getId() {
        return id;
    }
    public String getPoster_path() {
        return poster_path;
    }
    public String getBackdrop_path() {
        return backdrop_path;
    }
    public String getTitle() {
        return title;
    }
    public String getRelease_date() {
        return release_date;
    }
    public String getOverview() {
        return overview;
    }
    public Double getVote_average() {
        return vote_average;
    }

    //Setter
    public void setId(long id) {
        this.id = id;
    }
    public void setPoster_path(String poster_path) {this.poster_path = poster_path; }

    /*public Movie(long id, String title, String overview, String release_date) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
    }*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.poster_path);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.title);
        dest.writeString(this.release_date);
        dest.writeString(this.overview);
        dest.writeValue(this.vote_average);
    }

    private Movie(Parcel in) {
        this.id = in.readLong();
        this.poster_path = in.readString();
        this.backdrop_path = in.readString();
        this.title = in.readString();
        this.release_date = in.readString();
        this.overview = in.readString();
        this.vote_average = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

}
