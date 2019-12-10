package com.twinedo.consumerthemoviecatalogue.object;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShows implements Parcelable {

    //initial
    public long id;
    private String poster_path;
    private String backdrop_path;
    private String name;
    private String first_air_date;
    private String overview;
    private Double vote_average;

    public TvShows() { }

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
    public String getName() {
        return name;
    }
    public String getFirst_air_date() {
        return first_air_date;
    }
    public String getOverview() {
        return overview;
    }
    public Double getVote_average() {
        return vote_average;
    }

    // Setter
    public void setId(long id) {
        this.id = id;
    }
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
    public void setName(String name) {
        this.name = name;
    }

    public TvShows(long id, String poster_path, String backdrop_path, String name, String first_air_date, String overview, Double vote_average) {
        this.id = id;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.name = name;
        this.first_air_date = first_air_date;
        this.overview = overview;
        this.vote_average = vote_average;
    }

    /*public TvShows(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.name = getColumnString(cursor, DatabaseContract.FavoriteColumns.TITLE);
        this.poster_path = getColumnString(cursor, DatabaseContract.FavoriteColumns.POSTER);
        this.backdrop_path = getColumnString(cursor, DatabaseContract.FavoriteColumns.BACKDROP);
        this.first_air_date = getColumnString(cursor, DatabaseContract.FavoriteColumns.RELEASE);
        this.vote_average = getColumnDouble(cursor, DatabaseContract.FavoriteColumns.VOTE);
        this.overview = getColumnString(cursor, DatabaseContract.FavoriteColumns.OVERVIEW);
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
        dest.writeString(this.name);
        dest.writeString(this.first_air_date);
        dest.writeString(this.overview);
        dest.writeValue(this.vote_average);
    }

    private TvShows(Parcel in) {
        this.id = in.readLong();
        this.poster_path = in.readString();
        this.backdrop_path = in.readString();
        this.name = in.readString();
        this.first_air_date = in.readString();
        this.overview = in.readString();
        this.vote_average = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<TvShows> CREATOR = new Creator<TvShows>() {
        @Override
        public TvShows createFromParcel(Parcel source) {
            return new TvShows(source);
        }

        @Override
        public TvShows[] newArray(int size) {
            return new TvShows[size];
        }
    };
}
