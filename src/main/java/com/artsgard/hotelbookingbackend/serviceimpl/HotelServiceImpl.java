package com.artsgard.hotelbookingbackend.serviceimpl;

import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artsgard.hotelbookingbackend.repository.HotelRepository;
import com.artsgard.hotelbookingbackend.service.HotelService;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    
    @Autowired
    HotelRepository hotelRepo;

    @Override
    public List<HotelEntity> getAllHotels() {
        return hotelRepo.findAll();
    }
    
    @Override
    public HotelEntity getHotelById(Long id) {
        Optional<HotelEntity> optHotel = hotelRepo.findById(id);
        if(optHotel.isPresent()) {
            return optHotel.get();
        } else {
           throw new ResourceNotFoundException("no Clients found!");
        }
    }
}
