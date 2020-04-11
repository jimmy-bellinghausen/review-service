package com.example.entities;

public class ReviewUpdateRequest {
    String userEmail;
    Review review;
    public ReviewUpdateRequest(String userEmail, Review review) {
        this.userEmail=userEmail;
        this.review =review;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
