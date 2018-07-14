package com.arturofilio.fitchain.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Exercise implements Parcelable {

    private String imgURL;
    private String name;

    public Exercise(String imgURL, String name) {
        this.imgURL = imgURL;
        this.name = name;
    }

    protected Exercise(Parcel in) {
        name = in.readString();
        imgURL = in.readString();
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imgURL);
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
