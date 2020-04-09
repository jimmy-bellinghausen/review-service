package com.example.controllers;

import com.example.entities.Review;
import com.example.services.RestTemplateService;
import com.example.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    ReviewService reviewService;
    RestTemplateService restTemplateService;

    public ReviewController(ReviewService service, RestTemplateService restTemplateService) {
        this.reviewService = service;
        this.restTemplateService = restTemplateService;
    }

    @PostMapping
    public ResponseEntity<Review> postReview(@RequestBody Review reviewToPost){
        if(restTemplateService.validate(reviewToPost.getImdbId())){
            return ResponseEntity.ok(reviewService.postReview(reviewToPost));
        }
        return null;
    }
}
