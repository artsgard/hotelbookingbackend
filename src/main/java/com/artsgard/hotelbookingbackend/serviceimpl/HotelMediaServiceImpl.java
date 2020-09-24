package com.artsgard.hotelbookingbackend.serviceimpl;

import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import com.artsgard.hotelbookingbackend.entity.HotelMediaEntity;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import com.artsgard.hotelbookingbackend.repository.HotelMediaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artsgard.hotelbookingbackend.repository.HotelRepository;
import com.artsgard.hotelbookingbackend.service.HotelMediaService;
import com.artsgard.hotelbookingbackend.service.HotelService;
import java.util.Optional;

@Service
public class HotelMediaServiceImpl implements HotelMediaService {
    
    @Autowired
    HotelMediaRepository hotelMediaRepo;

    @Override
    public List<HotelMediaEntity> getAllHotelMedias() {
        return hotelMediaRepo.findAll();
    }

    @Override
    public HotelMediaEntity getHotelMediaById(Long id) {
        Optional<HotelMediaEntity> optHotelMedia = hotelMediaRepo.findById(id);
        if(optHotelMedia.isPresent()) {
            return optHotelMedia.get();
        } else {
           throw new ResourceNotFoundException("no Clients found!");
        }
    }
}

