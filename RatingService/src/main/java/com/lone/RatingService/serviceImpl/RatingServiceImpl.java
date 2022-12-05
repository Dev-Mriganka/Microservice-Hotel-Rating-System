package com.lone.RatingService.serviceImpl;

import com.lone.RatingService.entity.*;
import com.lone.RatingService.exceptions.RatingException;
import com.lone.RatingService.external.UserService;
import com.lone.RatingService.repo.RatingRepo;
import com.lone.RatingService.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepo ratingRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(RatingServiceImpl.class);

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepo.save(rating);
    }

    @Override
    public Rating updateRating(Rating rating) {
        ratingRepo.findById(rating.getRatingId())
                .orElseThrow(() -> new RatingException("Rating not found for this id :: " + rating.getRatingId()));

        rating.setRating(rating.getRating());
        rating.setComment(rating.getComment());

        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public List<UserRatingDto> getRatingByUserId(Integer userId) {

        List<Rating> userRatings = ratingRepo.findByUserId(userId);

        return userRatings.stream().map(
            rating -> {
                UserRatingDto ratingDto = new UserRatingDto();
                ResponseEntity<Hotel> hotel = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
                ratingDto.setRatingId(rating.getRatingId());
                ratingDto.setRating(rating.getRating());
                ratingDto.setComment(rating.getComment());
                ratingDto.setHotel(hotel.getBody());
                logger.info("Response Status Code: " + hotel.getStatusCode());
                return ratingDto;
            }
        ).collect(Collectors.toList());

        // return null;

    }

    @Override
    public List<HotelRatingDto> getRatingByHotelId(Integer hotelId) {

        List<Rating> userRatings = ratingRepo.findByHotelId(hotelId);

        System.out.println(userRatings);

        return userRatings.stream().map(
            rating -> {
                HotelRatingDto hotelRatingDto = new HotelRatingDto();
                // User user = userService.getHotelUserById(rating.getUserId());
                // System.out.println(user);
                hotelRatingDto.setRatingId(rating.getRatingId());
                hotelRatingDto.setRating(rating.getRating());
                hotelRatingDto.setComment(rating.getComment());
                // hotelRatingDto.setUser(user);
                return hotelRatingDto;
            }
        ).collect(Collectors.toList());
        // return null;

    }
}

