package com.hotel.hotelService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/team")
public class PlayersController {

    @GetMapping("/getAllPlayers")
    public ResponseEntity<List<String>> getAllPlayers()
    {
        List<String> playersList = List.of("sachin", "sehwag", "ganguly");

        return new ResponseEntity<>(playersList, HttpStatus.OK);
    }

}
