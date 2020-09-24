package com.artsgard.hotelbookingbackend.repository;

import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
    
}
