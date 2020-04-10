package com.example.controllers;

import com.example.services.ValidationService;
import com.example.services.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {

    @Autowired
    MockMvc mvc;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    ReviewService reviewService;

    @MockBean
    ValidationService validationService;

//    Post a new review for a user - Users are allowed to post 1 review per movie. The movie id must be validated using the movie service. Each review should have the following attributes...
//          User's email address
//          Review Title
//          Star Rating (1 - 5)
//          imdbId of the movie being reviewed
//          Review Text
//    Retrieve all reviews for a user. Include the movie title in the response, as well as a link to the movie details (served by the movie service)
//    Retrieve all reviews for a movie (imdb id) - response should include the movie title, and a link to the movie details (served by the movie service)
//    Update a review's title, text, star rating. Validate the user's email address on the post with the review being updated.
//    Delete a review. User's email on the request MUST match the review being deleted.`


    @Test
    void postReview() {
        //create a review

        //review to post with id to validate.
        //first validate id
        //service call to restTemplate service

        //post review or get the movie details

        //post review or get the movie details (the one you didn't do before)

        //mock mvc portion

        //customary to do all validation inside
        //service so you have service calling a service

        //controller responsible for receiving, calling things, and then returning
        //it shouldn't do any logic like checking if id of movie sent in is valid
        //the review service should call validation, which is your RestTemplateService.

        //don't wan't to put hardly any logic in controller
        //controller directs everything back and forth.
        //model calls business services. it's your representation of data


    }
}
