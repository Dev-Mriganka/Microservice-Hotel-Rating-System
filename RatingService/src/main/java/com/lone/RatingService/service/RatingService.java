package com.lone.RatingService.service;


import com.lone.RatingService.entity.*;

import java.util.List;

public interface RatingService {

    //create rating
    Rating createRating(Rating rating);

    //update rating
    Rating updateRating(Rating rating);

    //get all ratings
    List<Rating> getAllRatings();

    //get rating by user id
    List<UserRatingDto> getRatingByUserId(Integer userId);

    //get rating by hotel id
    List<HotelRatingDto> getRatingByHotelId(Integer hotelId);

}
