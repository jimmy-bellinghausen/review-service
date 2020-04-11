package com.example.services;

import com.example.entities.Review;
import com.example.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    ReviewRepository repository;
    ValidationService validationService;

    public ReviewService(ReviewRepository repository, ValidationService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }


    public Review postReview(Review reviewToBeSaved) {
        if(validationService.validate(reviewToBeSaved.getImdbId())){
            List<Review> reviews = repository.findAllByUserEmail(reviewToBeSaved.getUserEmail());
            boolean containsImdbId = false;
            for(Review review : reviews){
                if(review.getImdbId().equals(reviewToBeSaved.getImdbId())){
                    containsImdbId = true;
                }
            }
            if(!containsImdbId) {
                return repository.save(reviewToBeSaved);
            }
        }
        return null;
    }

    public List<Review> getAllByUserEmail(String userEmail) {
        return repository.findAllByUserEmail(userEmail);
    }

    public List<Review> getAllByMovieId(String imdbId) {
        return repository.findAllByImdbId(imdbId);
    }

    public Review updateReview(long id, Review newValues, String userEmail) {
        if(!repository.existsById(id)) { return null; }
        Review reviewToUpdate = repository.findById(id).get();
        if(!reviewToUpdate.getUserEmail().equals(userEmail)){ return null; }
        reviewToUpdate.update(newValues);
        return repository.save(reviewToUpdate);
    }

    public boolean deleteReview(long id, String userEmail) {
        if (!repository.existsById(id)) {
            return false;
        }
        if(!userEmail.equals(repository.findById(id).get().getUserEmail())) {
            return false;
        }
        return repository.deleteById(id) == 1;
    }
}
