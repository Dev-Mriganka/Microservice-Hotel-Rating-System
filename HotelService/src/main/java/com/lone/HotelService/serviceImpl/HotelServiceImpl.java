package com.lone.HotelService.serviceImpl;

import com.lone.HotelService.entity.*;
import com.lone.HotelService.exceptions.HotelException;
import com.lone.HotelService.external.RatingService;
import com.lone.HotelService.repo.HotelRepo;
import com.lone.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private RatingService ratingService;

    @Override
    public Hotel createHotel(Hotel hotel) {
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getHotelById(Integer id) {
        Hotel hotel = hotelRepo.findById(id)
                .orElseThrow(() -> new HotelException("Hotel with id " + id + " does not exist"));

        List<Rating> ratings = ratingService.getRating(id);
        hotel.setRatings(ratings);
        return hotel;

    }

    @Override
    public String deleteHotel(Integer id) {
        hotelRepo.deleteById(id);
        return "Hotel with id " + id + " deleted";
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        hotelRepo.findById(hotel.getId())
                .orElseThrow(() -> new HotelException("Hotel with id " + hotel.getId() + " does not exist"));
        return hotelRepo.save(hotel);
    }
}

