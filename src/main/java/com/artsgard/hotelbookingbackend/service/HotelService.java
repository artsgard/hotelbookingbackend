package com.artsgard.hotelbookingbackend.service;

import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface HotelService {
    List<HotelEntity> getAllHotels();  
    HotelEntity getHotelById(Long id);
    HotelEntity saveHotel(HotelEntity holelEntity);
    HotelEntity updateHotel(HotelEntity holelEntity) throws ResourceNotFoundException;
    void deleteHotelById(Long id) throws ResourceNotFoundException;
}
