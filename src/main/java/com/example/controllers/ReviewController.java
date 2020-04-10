package com.example.controllers;

import com.example.entities.Review;
import com.example.entities.ReviewMovieAndRating;
import com.example.models.MovieModel;
import com.example.services.MovieInfoService;
import com.example.services.ValidationService;
import com.example.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    ReviewService reviewService;
    MovieInfoService movieInfoService;


    public ReviewController(ReviewService service, MovieInfoService movieInfoService) {
        this.reviewService = service;
        this.movieInfoService = movieInfoService;
    }

    @PostMapping
    ResponseEntity<ReviewMovieAndRating> reviewInfoDTOResponseEntity(@RequestBody Review review) {
        Review reviewInfo = reviewService.postReview(review);
        MovieModel movieModelInfo = movieInfoService.getMovieInfo(review.getImdbId());
        return ResponseEntity.ok(reviewInfo);
    }

}
