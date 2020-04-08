package com.example.services;

import com.example.entities.Review;
import com.example.repositories.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import  static org.mockito.ArgumentMatchers.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
public class ReviewServiceTest {
    @MockBean
    ReviewRepository repository;

    Review expectedReview;
    Review expectedReview2;
    List<Review> reviewList;
    ReviewService service;

    @BeforeEach
    public void setExpecteds(){
        expectedReview = new Review();
        expectedReview2 = new Review();
        reviewList = new ArrayList<>();
        service = new ReviewService(repository);
    }

    //    Post a new review for a user - Users are allowed to post 1 review per movie. The movie id must be validated using the movie service. Each review should have the following attributes...
//          User's email address
//          Review Title
//           Star Rating (1 - 5)
//           imdbId of the movie being reviewed
//          Review Text
//    Retrieve all reviews for a user. Include the movie title in the response, as well as a link to the movie details (served by the movie service)
//    Retrieve all reviews for a movie (imdb id) - response should include the movie title, and a link to the movie details (served by the movie service)
//    Update a review's title, text, star rating. Validate the user's email address on the post with the review being updated.
//    Delete a review. User's email on the request MUST match the review being deleted.

    @Test
    public void postReview(){
        expectedReview.setUserEmail("AnEmail@email.com");
        expectedReview.setImdbId("A valid id");
        when(repository.findAllImdbIdByUserEmail(anyString())).thenReturn(Collections.EMPTY_LIST);
        when(repository.save(any(Review.class))).thenReturn(expectedReview);
        assertEquals(expectedReview, service.postReview(expectedReview));
        List<String> imdbIdList = new ArrayList<>();
        imdbIdList.add(expectedReview.getImdbId());
        reset(repository);
        when(repository.findAllImdbIdByUserEmail(anyString())).thenReturn(imdbIdList);
        assertNull(service.postReview(expectedReview));
    }

    @Test
    public void getAllReviewsByEmail(){
        reviewList.add(expectedReview);
        reviewList.add(expectedReview2);
        expectedReview.setUserEmail("TheEmail@email.com");
        expectedReview.setImdbId("an Id");
        expectedReview2.setUserEmail(expectedReview.getUserEmail());
        expectedReview2.setImdbId("another Id");
        expectedReview.setId(1L);
        expectedReview2.setId(2L);
        when(repository.findAllByUserEmail(anyString())).thenReturn(reviewList);
        assertEquals(reviewList, service.getAllByUserEmail(expectedReview.getUserEmail()));
    }

    @Test
    public void getAllReviewsByMovieId(){
        reviewList.add(expectedReview);
        reviewList.add(expectedReview2);
        expectedReview.setUserEmail("TheEmail@email.com");
        expectedReview.setImdbId("The Id");
        expectedReview2.setUserEmail("AnotherEmail@notEmail.com");
        expectedReview2.setImdbId(expectedReview.getImdbId());
        expectedReview.setId(1L);
        expectedReview2.setId(2L);
        when(repository.findAllByImdbId(anyString())).thenReturn(reviewList);
        assertEquals(reviewList, service.getAllByMovieId(expectedReview.getImdbId()));
    }

    @Test
    public void updateReview(){
        expectedReview.setUserEmail("TheEmail@email.com");
        expectedReview.setId(1L);
        expectedReview2.setTitle("New Title");
        expectedReview2.setText("New Text");
        expectedReview2.setStarRating(Review.STARRATING.FIVE);
        expectedReview2.setUserEmail(expectedReview.getUserEmail());
        expectedReview2.setId(expectedReview.getId());
        when(repository.findById(anyLong())).thenReturn(Optional.of(expectedReview));
        when(repository.save(any(Review.class))).thenAnswer(input->input.getArguments()[0]);
        assertEquals(expectedReview2, service.updateReview(expectedReview.getId(), expectedReview2));
        reset(repository);
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        assertEquals(null, service.updateReview(expectedReview.getId(), expectedReview2));
        expectedReview2.setUserEmail("Definitely not the same email.");
        reset(repository);
        when(repository.findById(anyLong())).thenReturn(Optional.of(expectedReview));
        assertEquals(null, service.updateReview(expectedReview.getId(), expectedReview2));
    }
}
