package com.artsgard.hotelbookingbackend.service;

import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import com.artsgard.hotelbookingbackend.entity.HotelMediaEntity;
import java.util.List;

/**
 *
 * @author artsgard
 */
public interface HotelMediaService {
    List<HotelMediaEntity> getAllHotelMedias(); 
    HotelMediaEntity getHotelMediaById(Long id);
}
