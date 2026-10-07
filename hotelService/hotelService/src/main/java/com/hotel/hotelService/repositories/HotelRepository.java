package com.hotel.hotelService.repositories;

import com.hotel.hotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface HotelRepository extends JpaRepository<Hotel,String> {
}
