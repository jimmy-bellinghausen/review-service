package com.example.services;

import com.example.models.MovieModel;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@Transactional
public class ValidationService {

    @Value("${VALIDATION_URL}")
    private String BASEURL;

    RestTemplate restTemplate;

    public ValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean validate(String imdbId) {
        return restTemplate.getForObject(BASEURL +imdbId, Boolean.class);
    }
}
