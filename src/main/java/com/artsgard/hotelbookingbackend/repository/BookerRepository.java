package com.artsgard.hotelbookingbackend.repository;

import com.artsgard.hotelbookingbackend.entity.BookerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookerRepository extends JpaRepository<BookerEntity, Long> {
    
}
