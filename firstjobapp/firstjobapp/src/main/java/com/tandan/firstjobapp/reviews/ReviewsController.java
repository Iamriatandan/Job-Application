package com.tandan.firstjobapp.reviews;

import com.tandan.firstjobapp.company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewsController {
    private ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Reviews>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewsService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReviews(@PathVariable Long companyId ,@RequestBody Reviews reviews) {
        boolean isReviewSaved = reviewsService.createReview(companyId, reviews);
        if (isReviewSaved) {
            return new ResponseEntity<>("Review Created successfully", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Review not saved",HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReviews(@PathVariable Long companyId,
                                                @PathVariable Long reviewId ,
                                                @RequestBody Reviews reviews){
        boolean isReviewUpdate = reviewsService.updateReviews(companyId,reviewId,reviews);
        if(isReviewUpdate){
            return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Review not UPDATED",HttpStatus.NOT_FOUND);

        }
    }
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long companyId,
                                                @PathVariable Long reviewId){
        boolean isDeleted = reviewsService.deleteReviewsById(companyId,reviewId);
        if(isDeleted){
            return new ResponseEntity<>("Reviews Successfully deleted",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Reviews not found",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Reviews> getCompanyById(@PathVariable Long companyId,@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewsService.getReviewsById(companyId,reviewId),HttpStatus.OK);
    }
}
