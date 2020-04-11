package com.example.controllers;

import com.example.entities.Review;
import com.example.entities.ReviewMovieAndRating;
import com.example.models.MovieModel;
import com.example.services.MovieInfoService;
import com.example.services.ValidationService;
import com.example.services.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {

    @Autowired
    MockMvc mvc;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    ReviewService reviewService;

    @MockBean
    MovieInfoService movieInfoService;

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
    void postReview() throws Exception {
        Review review = new Review();
        review.setImdbId("foo");
        review.setId(1l);
        String jsonPost = mapper.writeValueAsString(review);

        MovieModel movieModel = new MovieModel();
        movieModel.setTitle("Jackeass 1");

        when(reviewService.postReview(any(Review.class))).thenReturn(review);

        when(movieInfoService.getMovieInfo(anyString())).thenReturn(movieModel);

        mvc.perform(post("/api/review").content(jsonPost).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.imdbId").value(review.getImdbId()))
                .andExpect(jsonPath("$.movieModel").value(movieModel));


        //create a review--

        //review to post with id to validate.--
        //first validate id--


        //post review or get the movie details--

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

    @Test
    void getAllReviewsFromUser() throws Exception {

        Review review = new Review();
        review.setUserEmail("asahi@gmail.com");
        review.setId(1l);
        String reviewJson = review.getUserEmail();
        review.setImdbId("foo");

        MovieModel movieModel = new MovieModel();
        movieModel.setTitle("Jackass 4");
        ModelMapper modelMapper = new ModelMapper();

        List<Review> listReviews = new ArrayList<>();
        listReviews.add(review);


        when(reviewService.getAllByUserEmail(anyString())).thenReturn(listReviews);

        when(movieInfoService.getMovieInfo(anyString())).thenReturn(movieModel);

        ReviewMovieAndRating reviewMovieAndRating = modelMapper.map(review, ReviewMovieAndRating.class);

        reviewMovieAndRating.setMovieModel(movieModel);

        mvc.perform(get("/api/review/" + reviewJson).content(reviewJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(reviewMovieAndRating));

//        Retrieve all reviews for a user.
//        Include the movie title in the response, as well as a
//        link to the movie details
//        (served by the movie service)
    }
}
