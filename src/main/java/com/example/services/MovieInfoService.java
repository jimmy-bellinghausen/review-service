package com.example.services;

import com.example.models.MovieModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@Transactional
public class MovieInfoService {

    RestTemplate restTemplate;
    ValidationService validationService;
    @Value("${MOVIE_URL}")
    private String BASEURL;

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
            String moviePath = BASEURL + imdbId;
            MovieModel returnModel = restTemplate.getForObject( moviePath, MovieModel.class);
            returnModel.setLink(moviePath);
            return returnModel;
        }
        return null;
    }
}
