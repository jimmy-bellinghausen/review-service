package com.example.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Review {
    @Id
    @Column(unique = true, nullable = false)
    String userEmail;
    String title;
    STARRATING starRating;
    String imdbId;

    public Review() {
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

    public STARRATING getStarRating() {
        return starRating;
    }

    public void setStarRating(STARRATING starRating) {
        this.starRating = starRating;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(getUserEmail(), review.getUserEmail()) &&
                Objects.equals(getTitle(), review.getTitle()) &&
                getStarRating() == review.getStarRating() &&
                Objects.equals(getImdbId(), review.getImdbId());
    }

    @Override
    public String toString() {
        return "Review{" +
                "userEmail='" + userEmail + '\'' +
                ", title='" + title + '\'' +
                ", starRating=" + starRating +
                ", imdbId='" + imdbId + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserEmail(), getTitle(), getStarRating(), getImdbId());
    }

    static enum STARRATING{
        ONE,TWO,THREE,FOUR,FIVE
    }
}
