package com.lone.RatingService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("user_rating")
public class Rating {

    @Id
    private String ratingId;
    private Integer userId;
    private Integer hotelId;
    private int rating;
    private String comment;

}
