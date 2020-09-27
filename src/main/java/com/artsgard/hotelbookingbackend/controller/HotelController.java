package com.artsgard.hotelbookingbackend.controller;

import com.artsgard.hotelbookingbackend.DTO.BookerDTO;
import com.artsgard.hotelbookingbackend.DTO.ClientDTO;
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
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    
    org.slf4j.Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelService hotelService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<HotelEntity>> getAllHotels() {
        List<HotelEntity> list = hotelService.getAllHotels();
        return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<HotelEntity> getHotelById(Long id) {
        return new ResponseEntity<>(hotelService.getHotelById(id), HttpStatus.OK);
    }
    
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<HotelEntity> saveHotel(@Valid @RequestBody HotelEntity hotelEntity) {
        HotelEntity hotel = hotelService.saveHotel(hotelEntity);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
        //return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<HotelEntity> updateHotel(@Valid @RequestBody HotelEntity hotelEntity) {
        return new ResponseEntity<>(hotelService.updateHotel(hotelEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HotelEntity> deleteHotelById(@PathVariable("id") Long id) {
        hotelService.deleteHotelById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}