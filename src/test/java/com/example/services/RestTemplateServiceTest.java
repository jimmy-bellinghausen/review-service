package com.example.services;

import com.example.models.MovieModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RestTemplateServiceTest {

    @MockBean
    RestTemplate restTemplate;

    RestTemplateService service;

    @BeforeEach
    private  void setup(){
        service = new RestTemplateService(restTemplate);
    }

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
    void validateMovie() {
        String imdbId = "testId";
        MovieModel returnModel = new MovieModel();
        returnModel.setActors("Actor1, Actor2");
        when(restTemplate.getForEntity(anyString(), any(Class.class))).thenReturn(ResponseEntity.ok(returnModel));
        assertTrue(service.validate(imdbId));
    }
}