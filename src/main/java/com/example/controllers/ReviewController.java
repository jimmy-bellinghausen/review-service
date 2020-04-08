package com.example.controllers;

import com.example.entities.Review;
import com.example.models.MovieModel;
import com.example.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    ReviewService service;
    RestTemplate restTemplate;

    public ReviewController(RestTemplate restTemplate, ReviewService service) {
        this.service = service;
        this.restTemplate = restTemplate;
    }

//    @PostMapping
//    public ResponseEntity<Review> postReview(@RequestBody Review reviewToPost){
//        if(reviewToPost.getImdbId()!=null &&
//                restTemplate.getForObject("/api/movie/"+reviewToPost.getImdbId(), MovieModel.class)!=null);
//        return null;
//    }
}
