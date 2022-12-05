package com.lone.HotelService.controller;

import com.lone.HotelService.entity.Hotel;
import com.lone.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    //Get a single hotel
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(hotelService.getHotelById(id), HttpStatus.OK);
    }

    //Create a new Hotel
    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
    }

    //Get all hotels
    @GetMapping("/all")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
    }

}
