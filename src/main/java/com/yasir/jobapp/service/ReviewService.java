package com.yasir.jobapp.service;

import com.yasir.jobapp.entities.Review;

import java.util.List;

public interface ReviewService {

    boolean createReview(Long companyId, Review review);
    List<Review> getAllReview(Long companyId);
    Review getReviewById(Long companyId, Long reviewId);
    Boolean updateReviewById(Long companyId, Long id, Review review);
    Boolean deleteReviewById(Long companyId, Long id);

}
