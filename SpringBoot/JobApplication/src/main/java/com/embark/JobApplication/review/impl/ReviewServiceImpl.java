package com.embark.JobApplication.review.impl;

import com.embark.JobApplication.company.Company;
import com.embark.JobApplication.company.CompanyService;
import com.embark.JobApplication.review.Review;
import com.embark.JobApplication.review.ReviewRepository;
import com.embark.JobApplication.review.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(companyService.getCompanyById(companyId)!= null){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId)!=null && reviewRepository.existsById(reviewId)){
            //deletion have some steps
            //1. Retrieve review object
            Review review = reviewRepository.findById(reviewId).orElse(null);
            //2. Retrieving the company
            Company company = review.getCompany();  //reason getting associated company we want to remove that from company object as well
            //3. Removing a review from company reference --because it is a bidirectional mapping
            company.getReviews().remove(review);
            //4. Remove all the references from company side and review side as well
            //explicitly setting company as null
            review.setCompany(null);
            //5. Updating the company after changes.
            companyService.updateCompany(company,companyId);
            //6. finally deleting the review
            reviewRepository.deleteById(reviewId);
            return true;
        }else{
            return false;
        }
    }

    private ReviewRepository reviewRepository;

    private CompanyService companyService;

    //Auto Inject the instance of this class at runtime - springboot take care of
    public ReviewServiceImpl(ReviewRepository reviewRepository,CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }


    //We need to manage if company id is null it should not add Review to which company????
    @Override
    public boolean addReview(Review review, Long companyId) {
        //step1 - to get company object  - inorder to fetch company we need companyService which does not exist in reviewService
        //step2 - create an instance of companyService in this class
        Company company = companyService.getCompanyById(companyId);
        if(company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }else{
        return false;
        }
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

}
