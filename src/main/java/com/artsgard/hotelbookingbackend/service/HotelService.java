package com.artsgard.hotelbookingbackend.service;

import com.artsgard.hotelbookingbackend.DTO.HotelDTO;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface HotelService {
    List<HotelDTO> getAllHotels() throws ResourceNotFoundException;
    HotelDTO getHotelById(Long id) throws ResourceNotFoundException;
    HotelDTO saveHotel(HotelDTO hotelDTO);
    HotelDTO updateHotel(HotelDTO hotelDTO) throws ResourceNotFoundException;
    void deleteHotelById(Long id) throws ResourceNotFoundException;
}
