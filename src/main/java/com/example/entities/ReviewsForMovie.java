package com.example.entities;

import com.example.models.MovieModel;

import java.util.List;
import java.util.Objects;

public class ReviewsForMovie {
    MovieModel movie;
    List<ReviewDetail> reviewDetails;

    public ReviewsForMovie() {
    }

    public ReviewsForMovie(MovieModel movie, List<ReviewDetail> reviewDetails) {
        this.movie = movie;
        this.reviewDetails = reviewDetails;
    }

    public MovieModel getMovie() {
        return movie;
    }

    public void setMovie(MovieModel movie) {
        this.movie = movie;
    }

    public List<ReviewDetail> getReviewDetails() {
        return reviewDetails;
    }

    public void setReviewDetails(List<ReviewDetail> reviewDetails) {
        this.reviewDetails = reviewDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewsForMovie that = (ReviewsForMovie) o;
        return Objects.equals(movie, that.movie) &&
                Objects.equals(reviewDetails, that.reviewDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, reviewDetails);
    }
}
