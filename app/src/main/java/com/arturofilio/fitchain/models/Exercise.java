package com.arturofilio.fitchain.models;

public class Exercise {

    private String imgURL;
    private String name;

    public Exercise(String imgURL, String name) {
        this.imgURL = imgURL;
        this.name = name;
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
