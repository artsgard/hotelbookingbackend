package com.artsgard.hotelbookingbackend.controller;

import com.artsgard.hotelbookingbackend.DTO.HotelDTO;
import com.artsgard.hotelbookingbackend.DTO.HotelMediaDTO;
import com.artsgard.hotelbookingbackend.service.HotelMediaService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<List<HotelMediaDTO>> getAllHotelMedias() {
        return new ResponseEntity<>(hotelMediaService.getAllHotelMedias(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<HotelMediaDTO> getHotelMediaById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(hotelMediaService.getHotelMediaById(id), HttpStatus.OK);
    }
    
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<HotelMediaDTO> saveHotelMedia(@Valid @RequestBody HotelMediaDTO hotelMediaDTO) {
        HotelMediaDTO hotel = hotelMediaService.saveHotelMedia(hotelMediaDTO);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
        //return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<HotelMediaDTO> updateHotelMedia(@Valid @RequestBody HotelMediaDTO hotelMediaDTO) {
        return new ResponseEntity<>(hotelMediaService.updateHotelMedia(hotelMediaDTO), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HotelMediaDTO> deleteHotelMediaById(@PathVariable("id") Long id) {
        hotelMediaService.deleteHotelMediaById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
