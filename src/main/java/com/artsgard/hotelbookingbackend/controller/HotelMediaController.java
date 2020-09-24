package com.artsgard.hotelbookingbackend.controller;

import com.artsgard.hotelbookingbackend.entity.HotelMediaEntity;
import com.artsgard.hotelbookingbackend.service.HotelMediaService;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author artsgard
 */
@RestController
@RequestMapping("/hotelMedia")
public class HotelMediaController {
    org.slf4j.Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelMediaService hotelMediaService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<HotelMediaEntity>> getAllHotelMedias() {
        return new ResponseEntity<>(hotelMediaService.getAllHotelMedias(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<HotelMediaEntity> getHotelMediaById(Long id) {
        return new ResponseEntity<>(hotelMediaService.getHotelMediaById(id), HttpStatus.OK);
    }
}
