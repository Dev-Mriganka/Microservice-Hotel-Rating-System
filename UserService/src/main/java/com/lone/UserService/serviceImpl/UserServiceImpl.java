package com.lone.UserService.serviceImpl;

import com.lone.UserService.entity.Rating;
import com.lone.UserService.entity.User;
import com.lone.UserService.entity.UserDto;
import com.lone.UserService.exceptions.UserException;
import com.lone.UserService.repo.UserRepo;
import com.lone.UserService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {

//        String randId  = UUID.randomUUID().toString();
        return userRepo.save(user);

    }

    @Override
    public List<User> getAllUsers() {

        return userRepo.findAll();

    }

    @Override
    public User getUserById(Integer userId) throws UserException {

        User user = userRepo.findById(userId).orElseThrow(
                () -> new UserException("User not found with id: " + userId)
        );

        ArrayList<Rating> ratings = restTemplate.getForObject("http://RATING-SERVICE/rating/user/" + user.getUserId(),
                ArrayList.class);
        
        // System.out.println("Ratings: " + ratings);

        logger.info("Ratings: " + ratings);

        System.out.println("Ratings: " + ratings);
        user.setRatings(ratings);

        return user;

    }

    @Override
    public String deleteUser(Integer userId) throws UserException{
        userRepo.findById(userId).orElseThrow(
                () -> new UserException("User not found with id: " + userId)
        );

        userRepo.deleteById(userId);

        return "User deleted with id: " + userId;

    }

    @Override
    public User updateUser(User user) throws UserException {

        userRepo.findById(user.getUserId()).orElseThrow(
                () -> new UserException("User not found with id: " + user.getUserId())
        );
        
        return userRepo.save(user);

    }

    @Override
    public UserDto getHotelUserById(Integer userId) throws UserException {
        
        User user = userRepo.findById(userId).orElseThrow(
                () -> new UserException("User not found with id: " + userId));

        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        System.out.println("User: " + userDto);
        return userDto;
    }
}
