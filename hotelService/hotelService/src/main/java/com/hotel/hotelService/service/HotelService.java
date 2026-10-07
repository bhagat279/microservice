package com.hotel.hotelService.service;

import com.hotel.hotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    // create Hotel
    Hotel createHotel(Hotel hotel);

    Hotel getSingleHotel(String hotelId);

    List<Hotel> getAllHotel();
}
