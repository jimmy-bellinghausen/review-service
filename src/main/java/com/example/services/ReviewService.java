package com.example.services;

import com.example.entities.Review;
import com.example.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }


    public Review postReview(Review reviewToBeSaved) {
        if(!repository.findAllImdbIdByUserEmail(reviewToBeSaved.getUserEmail()).contains(reviewToBeSaved.getImdbId())){
            return repository.save(reviewToBeSaved);
        }
        return null;
    }

    public List<Review> getAllByUserEmail(String userEmail) {
        return null;
    }
}
