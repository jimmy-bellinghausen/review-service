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

    @BeforeEach
    public void setExpecteds(){
        expectedReview = new Review();
        expectedReview2 = new Review();
        reviewList = new ArrayList<>();
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
        ReviewService service = new ReviewService(repository);

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
        ReviewService service = new ReviewService(repository);
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
}
