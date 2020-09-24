package com.artsgard.hotelbookingbackend.repository;

import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import com.artsgard.hotelbookingbackend.entity.HotelMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelMediaRepository extends JpaRepository<HotelMediaEntity, Long> {
    
}
