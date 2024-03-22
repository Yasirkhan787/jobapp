package com.yasir.jobapp.service.implimentation;

import com.yasir.jobapp.entities.Company;
import com.yasir.jobapp.entities.Review;
import com.yasir.jobapp.repository.ReviewRepository;
import com.yasir.jobapp.service.CompanyService;
import com.yasir.jobapp.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewImpl implements ReviewService {

    ReviewRepository reviewRepository;
    CompanyService companyService;

    public ReviewImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        Company company = companyService.findCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public List<Review> getAllReview(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                    .filter(review -> review.getId().equals(reviewId))
                    .findFirst()
                    .orElse(null);
    }

    @Override
    public Boolean updateReviewById(Long companyId, Long reviewId, Review updatedreview) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        Review review = reviews.stream()
                .filter(rev -> rev.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
            if (review != null) {
                review.setTitle(updatedreview.getTitle());
                review.setDescription(updatedreview.getDescription());
                review.setRating(updatedreview.getRating());
                reviewRepository.save(review);
                return true;
            }
        return false;
    }

    @Override
    public Boolean deleteReviewById(Long companyId, Long id) {
        try{
            reviewRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
