package com.example.services;

import com.example.models.MovieModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

    RestTemplate restTemplate;

    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean validate(String imdbId) {
        ResponseEntity<MovieModel> returnValue = restTemplate.getForEntity("/api/movie" +imdbId, MovieModel.class);
        boolean valid = returnValue.getBody() != null;
        return valid;
    }

    public MovieModel getMovieInfo(String imdbId) {
        return restTemplate.getForEntity("/api/movie" +imdbId, MovieModel.class).getBody();
    }
}
