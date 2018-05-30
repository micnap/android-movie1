package com.mickeywilliamson.mickey.popularmovies;

import java.util.Date;

public class Movie {
    private String title;
    private String image;
    private String plot;
    private int rating;
    private Date releaseDate;


    public void Movie() {}

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPlot() {
        return plot;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setReleaseDate(Date date) {
        this.releaseDate = date;
    }
}


