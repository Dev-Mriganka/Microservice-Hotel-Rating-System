package com.lone.RatingService.repo;

import com.lone.RatingService.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepo extends MongoRepository<Rating, String> {

    List<Rating> findByUserId(Integer userId);

    List<Rating> findByHotelId(Integer hotelId);

}

