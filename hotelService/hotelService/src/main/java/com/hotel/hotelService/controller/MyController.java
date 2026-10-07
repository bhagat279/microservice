package com.hotel.hotelService.controller;

import com.hotel.hotelService.entities.Hotel;
import com.hotel.hotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("hotel/")
public class MyController {

    @Autowired
    private HotelService hotelService;


    @PostMapping("postHotel")
    public Hotel createHotel(@RequestBody Hotel hotel)
    {
        Hotel hotel1 = this.hotelService.createHotel(hotel);

        return hotel1;
    }

    @GetMapping("getAllHotels")
    public List<Hotel> getAllHotel()
    {
        List<Hotel> allHotel = this.hotelService.getAllHotel();

        return allHotel;
    }


    @GetMapping("getHotelById/{id}")
    public Hotel getHotelById(@PathVariable String id)
    {
        Hotel singleHotel = this.hotelService.getSingleHotel(id);

        return singleHotel;
    }
}
