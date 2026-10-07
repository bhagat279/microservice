package com.user.service.UserService.external.services;

import com.user.service.UserService.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTAL-SERVICE")
public interface HotelService {

    @GetMapping("hotel/getHotelById/{id}")
    Hotel getHotel(@PathVariable("id") String id);

}
