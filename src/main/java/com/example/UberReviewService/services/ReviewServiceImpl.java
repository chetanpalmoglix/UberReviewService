package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Booking;
import com.example.UberReviewService.models.Driver;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.BookingRepository;
import com.example.UberReviewService.repositories.DriverRepository;
import com.example.UberReviewService.repositories.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.FetchNotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, BookingRepository bookingRepository, DriverRepository    driverRepository){
        this.reviewRepository=reviewRepository;
    }

    @Override
    public Optional<Review> findReviewById(Long id) throws EntityNotFoundException {
        Optional<Review> review;
        try {
            review = this.reviewRepository.findById(id);
            if (review.isEmpty()) {
                throw new EntityNotFoundException("Review with id " + id + " not found");
            }
        }catch (Exception e){
            e.printStackTrace();
            if(e.getClass() == EntityNotFoundException.class){
                throw new FetchNotFoundException("Review with id " + id + " not found", id);
            }
            throw new FetchNotFoundException("Unable to fetch, PLease try again later!", id);
        }
        return review;
    }

    @Override
    public List<Review> finalAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public boolean deleteReviewById(Long id) {
        try {
            Review review = this.reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            this.reviewRepository.delete(review);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Review publishReview(Review review) {
        return this.reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long id, Review newReviewData) {
        Review review = this.reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(newReviewData.getRating() != null){
            review.setRating(newReviewData.getRating());
        }
        if(newReviewData.getContent() != null){
            review.setContent(newReviewData.getContent());
        }
        return this.reviewRepository.save(review);
    }



//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
////        Review r = Review.builder()
////        .content("Amazing ride quality")
////        .rating(4.0).build();
////
////        Booking b = Booking
////                    .builder()
////                    .review(r)
////                    .endTime(new Date())
////                    .build();
////        bookingRepository.save(b);     // this code executes sql query
////        System.out.println(r);
////        System.out.println(r.getId());
////        List<Review> reviews = reviewRepository.findAll();
////        for(Review review : reviews) {
////            System.out.println(r.getContent());
////        }
////        Optional<Booking> b = bookingRepository.findById(6L);
////        if(b.isPresent()) {
////            bookingRepository.delete(b.get());
////        }
////        reviewRepository.deleteById(2L);
//
////        Optional<Driver> d = driverRepository.hqlFindByIdAndLicense(1L, "DL121212");
////        System.out.println(d.get().getName());
//        List<Long> driverIds=new ArrayList<>(Arrays.asList(1L,2L,3L,4L,5L,8L,9L));
//        List<Driver> drivers=driverRepository.findAllByIdIn(driverIds);
////        List<Booking>bookings=bookingRepository.findAllByDriverIn(drivers); this is when we don't want to use the subselect manual way
//        for (Driver driver: drivers){
//            List<Booking> bookings=driver.getBookings();
//            bookings.forEach(booking -> System.out.println(booking.getId()));
//        }
//    }
}
