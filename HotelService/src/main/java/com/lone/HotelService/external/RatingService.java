package com.lone.HotelService.external;

import com.lone.HotelService.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/rating/hotel/{id}")
    List<Rating> getRating(@PathVariable("id") Integer hotelId);

}
