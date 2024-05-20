package com.project.reviewms.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    /*
    REVIEWS
    -------------------
    GET /reviews?companyId={companyId}
    POST /reviews?companyId={companyId}
    GET  /reviews/{reviewId}
    PUT /reviews/{reviewId}
    DELETE /reviews/{reviewId}
     */

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Review review,
                                            @RequestParam Long companyId){
        boolean isReviewSaved = reviewService.addReview(companyId,review);
        if(isReviewSaved)
            return new ResponseEntity<>("Review added successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Review Not Saved...Company Not Found",HttpStatus.NOT_FOUND);
    }

    //get review of specific company
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(reviewId),HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review updatedReview){

        /*
        companyId - to against which company we need to change the reviews
        reviewId - which review we need to update
        review - updated review
         */
        boolean isReviewUpdated = reviewService.updateReview(reviewId,updatedReview);
        if(isReviewUpdated)
            return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Review not updated",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(reviewId);
        if(isReviewDeleted) {
            return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Review not deleted",HttpStatus.NOT_FOUND);
        }
    }

}
