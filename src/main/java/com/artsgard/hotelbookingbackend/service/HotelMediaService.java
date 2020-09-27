package com.artsgard.hotelbookingbackend.service;

import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import com.artsgard.hotelbookingbackend.entity.HotelMediaEntity;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import java.util.List;

/**
 *
 * @author artsgard
 */
public interface HotelMediaService {
    List<HotelMediaEntity> getAllHotelMedias(); 
    HotelMediaEntity getHotelMediaById(Long id);
    HotelMediaEntity saveHotelMedia(HotelMediaEntity hotelMediaEntity);
    HotelMediaEntity updateHotelMedia(HotelMediaEntity hotelMediaEntity) throws ResourceNotFoundException;
    void deleteHotelMediaById(Long id) throws ResourceNotFoundException;
}
