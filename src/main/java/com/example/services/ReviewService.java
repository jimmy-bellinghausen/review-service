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
        return repository.findAllByUserEmail(userEmail);
    }

    public List<Review> getAllByMovieId(String imdbId) {
        return repository.findAllByImdbId(imdbId);
    }

    public Review updateReview(long id, Review expectedReview2) {
        Review reviewToUpdate = repository.findById(id).orElse(null);
        if(reviewToUpdate==null){ return null; }
        if(!reviewToUpdate.getUserEmail().equals(expectedReview2.getUserEmail())){ return null; }
        reviewToUpdate.update(expectedReview2);
        return repository.save(reviewToUpdate);
    }
}
