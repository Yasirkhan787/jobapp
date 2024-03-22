package com.yasir.jobapp.controller;

import com.yasir.jobapp.entities.Review;
import com.yasir.jobapp.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Get all reviews of a specific company
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReview(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReview(companyId), HttpStatus.OK);
    }

    // Get a specific review of a specific company
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId,
                                                @PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReviewById(companyId, reviewId), HttpStatus.OK);
    }
    //
    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long companyId,
                                               @RequestBody Review review) {
        boolean isReviewSaved = reviewService.createReview(companyId, review);
        if (isReviewSaved) {
            return new ResponseEntity<>("Review Created Successfully", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Review Didn't Created", HttpStatus.NOT_MODIFIED);
        }
    }

    //
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReviewById(@PathVariable Long companyId,
                                                   @PathVariable Long reviewId,
                                                   @RequestBody Review review) {
        boolean update = reviewService.updateReviewById(companyId, reviewId, review);
        if (update) {
            return new ResponseEntity<>("Review Updated Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    //
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId,
                                                   @PathVariable Long reviewId){
        boolean delete = reviewService.deleteReviewById(companyId, reviewId);
        if (delete) {
            return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
}

