package com.project.reviewms.review.impl;

import com.project.reviewms.review.Review;
import com.project.reviewms.review.ReviewRepository;
import com.project.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review!= null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(updatedReview);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            reviewRepository.delete(review);
            return true;
        }else{
            return false;
        }
    }

    private final ReviewRepository reviewRepository;

    //Auto Inject the instance of this class at runtime - springboot take care of
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }


    //We need to manage if company id is null it should not add Review to which company????
    @Override
    public boolean addReview(Long companyId,Review review) {
        //step1 - to get company object  - inorder to fetch company we need companyService which does not exist in reviewService
        //step2 - create an instance of companyService in this class
        if(companyId!=null && review!=null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }else{
        return false;
        }
    }

    @Override
    public Review getReview(Long reviewId) {
            return reviewRepository.findById(reviewId).orElse(null);
          }

}
