package com.artsgard.hotelbookingbackend.controller;

import com.artsgard.hotelbookingbackend.DTO.BookerDTO;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.artsgard.hotelbookingbackend.service.BookerService;
import com.artsgard.hotelbookingbackend.service.ClientService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/booker")
public class BookerController {
    
    org.slf4j.Logger logger = LoggerFactory.getLogger(BookerController.class);

    @Autowired
    private BookerService bookerService;


    @Autowired
    private ClientService clientService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<BookerDTO>> findAllBookings() {
        return new ResponseEntity<>(bookerService.findAllBookings(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<BookerDTO> findBookingById(@PathVariable Long id) {
        return new ResponseEntity<>(bookerService.findBookingById(id), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<BookerDTO> saveBooking(@Valid @RequestBody BookerDTO bookingDTO) {
        BookerDTO client = bookerService.saveBooking(bookingDTO);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
        //return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<BookerDTO> updateBooking(@Valid @RequestBody BookerDTO bookingDTO) {
        return new ResponseEntity<>(bookerService.updateBooking(bookingDTO), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BookerDTO> deleteBookingById(@PathVariable("id") Long id) {
        bookerService.deleteBookingById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}