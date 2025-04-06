package com.tandan.firstjobapp.reviews;
import java.util.List;

public interface ReviewsService {
    List<Reviews> getAllReviews(Long CompanyId);
    boolean updateReviews(Long companyId,Long reviewId,Reviews updatedReviews);
    boolean createReview(Long companyId,Reviews reviews);
    boolean deleteReviewsById(Long companyId, Long reviewId);
    Reviews getReviewsById(Long companyId, Long reviewId);


}
