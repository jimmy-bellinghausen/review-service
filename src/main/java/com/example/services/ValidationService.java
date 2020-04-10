package com.example.services;

import com.example.models.MovieModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidationService {

    RestTemplate restTemplate;

    public ValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean validate(String imdbId) {
        return restTemplate.getForObject("/api/movie/exists/" +imdbId, Boolean.class);
    }

//    public MovieModel getMovieInfo(String imdbId) {
//        return restTemplate.getForObject("/api/movie/" +imdbId, MovieModel.class);
//    }
}
