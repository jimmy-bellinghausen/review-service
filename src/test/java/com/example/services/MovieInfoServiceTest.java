package com.example.services;

import com.example.models.MovieModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MovieInfoServiceTest {

    @MockBean
    RestTemplate restTemplate;

    @MockBean
    ValidationService validationService;

    @Test
    void getMovieInfo() {
        //want to validate the imdbId before we get the movie info inside of the method
        //how will we provide the variables required for the validate in the test?
        //when is like the assert. It adds functionality to our mock.
        MovieInfoService movieInfoService = new MovieInfoService(restTemplate, validationService);
        when(validationService.validate(anyString())).thenReturn(true);
        String imdbId = "testStr";
        MovieModel expectedModel = new MovieModel();
        expectedModel.setTitle("NewTitle");
        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(expectedModel);
        assertNotNull(movieInfoService.getMovieInfo(imdbId));
    }
}
