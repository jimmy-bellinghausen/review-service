package com.example.services;

import com.example.entities.Review;
import com.example.repositories.ReviewRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import  static org.mockito.ArgumentMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ReviewServiceTest {
    @MockBean
    ReviewRepository repository;

    @Test
    public void postReview(){
        ReviewService service = new ReviewService(repository);
        Review expected = new Review();
        expected.setUserEmail("AnEmail@email.com");
        when(repository.existsById(anyString())).thenReturn(false);
        when(repository.save(any(Review.class))).thenReturn(expected);
        assertEquals(expected, service.postReview(expected));
        reset(repository);
        when(repository.existsById(anyString())).thenReturn(true);
        assertNull(service.postReview(expected));
    }
}
