package com.example.entities;

import com.example.models.MovieModel;

import java.util.Objects;

public class ReviewMovieAndRating {
    MovieModel movieModel;
    private String userEmail;
    private String title;
    private Review.STARRATING starRating;
    private String imdbId;
    private String text;

    public ReviewMovieAndRating() {
    }

    public ReviewMovieAndRating(MovieModel movieModel, String userEmail, String title, Review.STARRATING starRating, String imdbId, String text) {
        this.movieModel = movieModel;
        this.userEmail = userEmail;
        this.title = title;
        this.starRating = starRating;
        this.imdbId = imdbId;
        this.text = text;
    }

    public MovieModel getMovieModel() {
        return movieModel;
    }

    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Review.STARRATING getStarRating() {
        return starRating;
    }

    public void setStarRating(Review.STARRATING starRating) {
        this.starRating = starRating;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ReviewMovieAndRating{" +
                "movieModel=" + movieModel +
                ", userEmail='" + userEmail + '\'' +
                ", title='" + title + '\'' +
                ", starRating=" + starRating +
                ", imdbId='" + imdbId + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewMovieAndRating that = (ReviewMovieAndRating) o;
        return Objects.equals(movieModel, that.movieModel) &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(title, that.title) &&
                starRating == that.starRating &&
                Objects.equals(imdbId, that.imdbId) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieModel, userEmail, title, starRating, imdbId, text);
    }
}
