package com.artsgard.hotelbookingbackend.service;

import com.artsgard.hotelbookingbackend.DTO.HotelMediaDTO;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import java.util.List;

/**
 *
 * @author artsgard
 */
public interface HotelMediaService {
    List<HotelMediaDTO> getAllHotelMedias() throws ResourceNotFoundException; 
    HotelMediaDTO getHotelMediaById(Long id) throws ResourceNotFoundException;
    HotelMediaDTO saveHotelMedia(HotelMediaDTO hotelMediaDTO);
    HotelMediaDTO updateHotelMedia(HotelMediaDTO hotelMediaDTO) throws ResourceNotFoundException;
    void deleteHotelMediaById(Long id) throws ResourceNotFoundException;
}
