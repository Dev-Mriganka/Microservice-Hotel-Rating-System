package com.lone.RatingService.controller;

import com.lone.RatingService.entity.*;
import com.lone.RatingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/hotel/{id}")
    private ResponseEntity<List<HotelRatingDto>> getRatingByHotelId(@PathVariable("id") Integer hotelId) {
        return ResponseEntity.ok().body(ratingService.getRatingByHotelId(hotelId));
    }

    @GetMapping("/user/{id}")
    private ResponseEntity<List<UserRatingDto>> getRatingByUserId(@PathVariable("id") Integer userId) {
        return ResponseEntity.ok().body(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/all")
    private ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok().body(ratingService.getAllRatings());
    }

    @PutMapping("/update")
    private ResponseEntity<Rating> updateRating(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ratingService.updateRating(rating));
    }

    @PostMapping("/rate")
    private ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }

}
