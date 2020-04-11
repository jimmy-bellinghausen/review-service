package com.example.entities;


import java.util.Objects;

public class ReviewDetail {
    private String userEmail;
    private String title;
    private Review.STARRATING starRating;
    private String imdbId;
    private String text;

    public ReviewDetail() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewDetail that = (ReviewDetail) o;
        return Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(title, that.title) &&
                starRating == that.starRating &&
                Objects.equals(imdbId, that.imdbId) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmail, title, starRating, imdbId, text);
    }

    @Override
    public String toString() {
        return "ReviewDetail{" +
                "userEmail='" + userEmail + '\'' +
                ", title='" + title + '\'' +
                ", starRating=" + starRating +
                ", imdbId='" + imdbId + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
