package com.artsgard.hotelbookingbackend.service;

import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface HotelService {
    List<HotelEntity> getAllHotels();  
    HotelEntity getHotelById(Long id);
}
