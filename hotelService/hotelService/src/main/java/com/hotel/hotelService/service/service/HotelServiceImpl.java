package com.hotel.hotelService.service.service;

import com.hotel.hotelService.entities.Hotel;
import com.hotel.hotelService.exception.ResourceNotFoundException;
import com.hotel.hotelService.repositories.HotelRepository;
import com.hotel.hotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomHotelId = UUID.randomUUID().toString();
        hotel.setHotelId(randomHotelId);
        return this.hotelRepository.save(hotel);
    }

    @Override
    public Hotel getSingleHotel(String hotelId) {
     return  hotelRepository.findById(hotelId).orElseThrow(() ->new ResourceNotFoundException("hotel not found"+ hotelId));
    }

    @Override
    public List<Hotel> getAllHotel() {
        List<Hotel> allHotel = this.hotelRepository.findAll();
        return allHotel;
    }
}
