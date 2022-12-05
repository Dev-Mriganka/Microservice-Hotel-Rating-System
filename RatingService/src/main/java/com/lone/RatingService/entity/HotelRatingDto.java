package com.lone.RatingService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelRatingDto {

    private String ratingId;
    private int rating;
    private String comment;
    private User user;

}
