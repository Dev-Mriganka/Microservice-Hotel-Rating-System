package com.lone.HotelService.service;

import com.lone.HotelService.entity.Hotel;

import java.util.List;

public interface HotelService {

    //create
    Hotel createHotel(Hotel hotel);

    //getAll
    List<Hotel> getAllHotels();

    //get by id
    Hotel getHotelById(Integer id);

    //delete hotel
    String deleteHotel(Integer id);

    //update hotel
    Hotel updateHotel(Hotel hotel);

}
