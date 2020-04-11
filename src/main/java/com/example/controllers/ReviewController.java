package com.example.controllers;

import com.example.entities.Review;
import com.example.entities.ReviewDetail;
import com.example.entities.ReviewMovieAndRating;
import com.example.entities.ReviewsForMovie;
import com.example.models.MovieModel;
import com.example.services.MovieInfoService;
import com.example.services.ValidationService;
import com.example.services.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    ReviewService reviewService;
    MovieInfoService movieInfoService;
    ModelMapper modelMapper = new ModelMapper();



    public ReviewController(ReviewService service, MovieInfoService movieInfoService) {
        this.reviewService = service;
        this.movieInfoService = movieInfoService;
    }

    @PostMapping
    ResponseEntity<ReviewMovieAndRating> reviewInfoDTOResponseEntity(@RequestBody Review review) {
        Review reviewInfo = reviewService.postReview(review);
        MovieModel movieModelInfo = movieInfoService.getMovieInfo(review.getImdbId());
        ReviewMovieAndRating reviewMovieRating = modelMapper.map(reviewInfo, ReviewMovieAndRating.class);
        reviewMovieRating.setMovieModel(movieModelInfo);
        return ResponseEntity.ok(reviewMovieRating);
    }

    @GetMapping("/{email}")
    ResponseEntity<List<ReviewMovieAndRating>> responseEntity(@PathVariable String email){
        List<Review> reviewList = reviewService.getAllByUserEmail(email);
        List<ReviewMovieAndRating> reviewMovieAndRatingList = new ArrayList<>();
        reviewList.forEach(review -> reviewMovieAndRatingList.add(modelMapper.map(review,ReviewMovieAndRating.class)));
        reviewMovieAndRatingList.forEach(reviewMovieAndRating -> reviewMovieAndRating.setMovieModel(movieInfoService.getMovieInfo(reviewMovieAndRating.getImdbId())));

        return ResponseEntity.ok(reviewMovieAndRatingList);

    }

    @GetMapping("/movie/{imdbId}")
    ResponseEntity<ReviewsForMovie> reviewsForMovieDTOResponseEntity(@PathVariable String imdbId){
        List<Review> reviewList = reviewService.getAllByMovieId(imdbId);
        List<ReviewDetail> reviewDetails = new ArrayList<>();
        reviewList.forEach(review -> reviewDetails.add(modelMapper.map(review, ReviewDetail.class)));
        //list of ReviewDetail and a MovieModel makes up ReviewsForMovie
        MovieModel movieModel = movieInfoService.getMovieInfo(imdbId);
        ReviewsForMovie reviews = new ReviewsForMovie(movieModel, reviewDetails);
        return ResponseEntity.ok(reviews);
    }
}

