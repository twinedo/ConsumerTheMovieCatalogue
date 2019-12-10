package com.twinedo.consumerthemoviecatalogue.object;

import android.os.Parcel;
import android.os.Parcelable;

public class Genres implements Parcelable {

    public long id;
    private String name;
    private String genres;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.genres);
    }

    private Genres(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.genres = in.readString();
    }

    public static final Creator<Genres> CREATOR = new Creator<Genres>() {
        @Override
        public Genres createFromParcel(Parcel source) {
            return new Genres(source);
        }

        @Override
        public Genres[] newArray(int size) {
            return new Genres[size];
        }
    };
}
