package com.twinedo.consumerthemoviecatalogue.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class TvShowsResult implements Parcelable {

    private final int page;
    public final ArrayList<TvShows> results;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeTypedList(this.results);
    }

    private TvShowsResult(Parcel in) {
        this.page = in.readInt();
        this.results = in.createTypedArrayList(TvShows.CREATOR);
    }

    public static final Creator<TvShowsResult> CREATOR = new Creator<TvShowsResult>() {
        @Override
        public TvShowsResult createFromParcel(Parcel source) {
            return new TvShowsResult(source);
        }

        @Override
        public TvShowsResult[] newArray(int size) {
            return new TvShowsResult[size];
        }
    };
}
