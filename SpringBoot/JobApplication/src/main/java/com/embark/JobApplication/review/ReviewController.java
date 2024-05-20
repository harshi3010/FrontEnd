package com.embark.JobApplication.review;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    /*
    REVIEWS
    -------------------
    GET /companies/{companyId}/reviews
    POST /companies/{companyId}/reviews
    GET  /companies/{companyId}/reviews/{reviewId}
    PUT /companies/{companyId}/reviews/{reviewId}
    DELETE /companies/{companyId}/reviews/{reviewId}
     */

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId),HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@RequestBody Review review,
                                            @PathVariable Long companyId){
        boolean isReviewSaved = reviewService.addReview(review,companyId);
        if(isReviewSaved)
            return new ResponseEntity<>("Review added successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Review Not Saved...Company Not Found",HttpStatus.NOT_FOUND);
    }

    //get review of specific company
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getAllReviewByCompanyId(@PathVariable Long companyId,@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(companyId,reviewId),HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review updatedReview){

        /*
        companyId - to against which company we need to change the reviews
        reviewId - which review we need to update
        review - updated review
         */
        boolean isReviewUpdated = reviewService.updateReview(companyId,reviewId,updatedReview);
        if(isReviewUpdated)
            return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Review not updated",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(companyId,reviewId);
        if(isReviewDeleted) {
            return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Review not deleted",HttpStatus.NOT_FOUND);
        }
    }

}
