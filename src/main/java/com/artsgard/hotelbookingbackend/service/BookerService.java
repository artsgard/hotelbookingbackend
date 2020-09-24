package com.artsgard.hotelbookingbackend.service;

import com.artsgard.hotelbookingbackend.DTO.BookerDTO;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 
 * @author WillemDragstra
 */
@Service
public interface BookerService  {
    List<BookerDTO> findAllBookings() throws ResourceNotFoundException;
    BookerDTO findBookingById(Long id) throws ResourceNotFoundException;
    BookerDTO saveBooking(BookerDTO socioDTO);
    BookerDTO updateBooking(BookerDTO socioDTO) throws ResourceNotFoundException;
    void deleteBookingById(Long id) throws ResourceNotFoundException;
}