package com.embarkx.Firstjobapp.review.Impl;

import com.embarkx.Firstjobapp.company.Company;
import com.embarkx.Firstjobapp.company.CompanyService;
import com.embarkx.Firstjobapp.review.Review;
import com.embarkx.Firstjobapp.review.ReviewRepository;
import com.embarkx.Firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewserviceImplementation implements ReviewService {
  private ReviewRepository reviewRepository;
  private CompanyService companyService;

    public ReviewserviceImplementation(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService=companyService;
    }

    @Override
    public List<Review> getAllReview(Long companyId) {
        List<Review>reviews=reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company=companyService.getCompanyById(companyId);
        if(company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
           List<Review>reviews=reviewRepository.findByCompanyId(companyId);

           return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updateReview) {
        if(companyService.getCompanyById(companyId)!=null){
               updateReview.setCompany(companyService.getCompanyById(companyId));
               updateReview.setId(reviewId);
               reviewRepository.save(updateReview);
               return true;
        }else{

            return false;
        }
    }

    @Override
    public boolean deleteReviw(Long companyId, Long reviewId) {

     if( companyService.getCompanyById(companyId)!=null && reviewRepository.existsById(reviewId)){
         Review review= reviewRepository.findById(reviewId).orElse(null);

         Company company=companyService.getCompanyById(companyId);
         company.getReviews().remove(review);
         review.setCompany(null);
         companyService.updateCompany(company,companyId);
         reviewRepository.deleteById(reviewId);
         return true;

     }

     return false;

    }
}
