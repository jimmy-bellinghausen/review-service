package com.example.services;

import com.example.models.MovieModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {

    RestTemplate restTemplate;
    ValidationService validationService;

    public MovieInfoService() {

    }

    public MovieInfoService(RestTemplate restTemplate, ValidationService validationService) {
        this.restTemplate = restTemplate;
        this.validationService = validationService;
    }


    public MovieModel getMovieInfo(String imdbId) {
        //make sure imbdId valid

        // retrieve that info

        // return that info

        // if not valid then return null

        // how will be validate imdbId?
        if (validationService.validate(imdbId)) {
            return restTemplate.getForObject("/api/movie/" + imdbId, MovieModel.class);
        }
        return null;
    }
}
