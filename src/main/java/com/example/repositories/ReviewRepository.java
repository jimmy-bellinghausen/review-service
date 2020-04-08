package com.example.repositories;

import com.example.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<String> findAllImdbIdByUserEmail(String userEmail);
}
