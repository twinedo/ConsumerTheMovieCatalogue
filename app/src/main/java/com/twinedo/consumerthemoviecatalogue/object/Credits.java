package com.twinedo.consumerthemoviecatalogue.object;

import android.os.Parcel;
import android.os.Parcelable;

public class Credits implements Parcelable {

    //initial
    public long id;
    private String profile_path;
    private String name;
    private String character;

    //Getter
    public long getId() { return id; }
    public String getProfile_path() {return profile_path; }
    public String getName() { return name; }
    public String getCharacter() { return character; }

    //Setter
    public void setId(long id) { this.id = id; }
    public void setName(String name) { this.name = name; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.profile_path);
        dest.writeString(this.name);
        dest.writeString(this.character);
    }

    private Credits(Parcel in) {
        this.id = in.readLong();
        this.profile_path = in.readString();
        this.name = in.readString();
        this.character = in.readString();
    }

    public static final Creator<Credits> CREATOR = new Creator<Credits>() {
        @Override
        public Credits createFromParcel(Parcel source) {
            return new Credits(source);
        }

        @Override
        public Credits[] newArray(int size) {
            return new Credits[size];
        }
    };
}
