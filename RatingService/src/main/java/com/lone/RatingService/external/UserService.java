package com.lone.RatingService.external;

import com.lone.RatingService.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserService {

   @GetMapping("/users/hotel/{userId}")
   User getHotelUserById(@PathVariable Integer userId);

}
