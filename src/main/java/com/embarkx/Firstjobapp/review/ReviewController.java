package com.embarkx.Firstjobapp.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
      private  ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReview(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
       if( reviewService.addReview(companyId,review)) {
           return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
       }else{
           return new ResponseEntity<>("Review not saved", HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/reviews/{reviewId}")
    public  ResponseEntity<Review>getReview(@PathVariable Long companyId, @PathVariable Long reviewId){

        return new ResponseEntity<>(reviewService.getReview(companyId,reviewId),HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId")
    public ResponseEntity<String>updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review ){
         boolean isupdated= reviewService.updateReview(companyId,reviewId,review);
         if(isupdated) {
             return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
         }
         return new ResponseEntity<String>("Something went wrong", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String>deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){

        if(reviewService.deleteReviw(companyId,reviewId)){
            return new ResponseEntity<>("Review deleted", HttpStatus.OK);
        }

        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

}
