package com.example.controllers;

import com.example.services.ValidationService;
import com.example.services.ReviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    ReviewService reviewService;
    ValidationService validationService;

    public ReviewController(ReviewService service, ValidationService validationService) {
        this.reviewService = service;
        this.validationService = validationService;
    }

}
