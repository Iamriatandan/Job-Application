package com.tandan.firstjobapp.reviews.impl;

import com.tandan.firstjobapp.company.Company;
import com.tandan.firstjobapp.company.CompanyService;
import com.tandan.firstjobapp.reviews.Reviews;
import com.tandan.firstjobapp.reviews.ReviewsRepository;
import com.tandan.firstjobapp.reviews.ReviewsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewsServiceImpl implements ReviewsService {
    private ReviewsRepository reviewsRepository;
    private CompanyService companyService;
    public ReviewsServiceImpl(ReviewsRepository reviewsRepository,CompanyService companyService) {
        this.reviewsRepository = reviewsRepository;
        this.companyService=companyService;
    }

    @Override
    public List<Reviews> getAllReviews(Long companyId) {
       List<Reviews> reviewsList= reviewsRepository.findByCompanyId(companyId);
       return reviewsList;
    }

    @Override
    public boolean updateReviews(Long companyId,Long reviewId,Reviews updatedReviews) {
       if(companyService.getCompanyById(companyId)!= null){
           updatedReviews.setCompany(companyService.getCompanyById(companyId));
           updatedReviews.setId(reviewId);
           reviewsRepository.save(updatedReviews);
           return true;
       }else{
           return false;
       }
    }

    @Override
    public boolean createReview(Long companyId,Reviews reviews) {
        Company company = companyService.getCompanyById(companyId);
        if(company!=null){
            reviews.setCompany(company);
            reviewsRepository.save(reviews);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean deleteReviewsById(Long companyId,Long reviewId) {
       if(companyService.getCompanyById(companyId) != null &&
       reviewsRepository.existsById(reviewId)){
           Reviews reviews = reviewsRepository.findById(reviewId).orElse(null);
           Company company = reviews.getCompany();
           company.getReviews().remove(reviews);
           reviews.setCompany(null);
           companyService.updateCompanies(company,companyId);
           reviewsRepository.deleteById(reviewId);
           return true;
       }
        return false;
    }

    @Override
    public Reviews getReviewsById(Long companyId,Long reviewId) {

        List<Reviews> reviewsList= reviewsRepository.findByCompanyId(companyId);
        return reviewsList.stream().filter(reviews -> reviews.getId().equals(reviewId)).findFirst().orElse(null);
    }
}
