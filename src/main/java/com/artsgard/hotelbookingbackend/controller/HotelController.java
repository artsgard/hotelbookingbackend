package com.artsgard.hotelbookingbackend.controller;

import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.artsgard.hotelbookingbackend.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    
    org.slf4j.Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelService hotelService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<HotelEntity>> getAllHotels() {
        return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<HotelEntity> getHotelById(Long id) {
        return new ResponseEntity<>(hotelService.getHotelById(id), HttpStatus.OK);
    }
}