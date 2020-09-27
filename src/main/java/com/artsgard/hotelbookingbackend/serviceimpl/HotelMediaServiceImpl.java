package com.artsgard.hotelbookingbackend.serviceimpl;

import com.artsgard.hotelbookingbackend.entity.HotelMediaEntity;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import com.artsgard.hotelbookingbackend.repository.HotelMediaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artsgard.hotelbookingbackend.service.HotelMediaService;
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
           throw new ResourceNotFoundException("no Hotel Media found!");
        }
    }
    
    @Override
    public HotelMediaEntity saveHotelMedia(HotelMediaEntity hotelMediaEntity) {
        return hotelMediaRepo.save(hotelMediaEntity);
    }

    @Override
    public HotelMediaEntity updateHotelMedia(HotelMediaEntity hotelMediaEntity) throws ResourceNotFoundException {
        Optional<HotelMediaEntity> optHotelMedia = hotelMediaRepo.findById(hotelMediaEntity.getId());
        if(optHotelMedia.isPresent()) {
            HotelMediaEntity ent = hotelMediaRepo.save(optHotelMedia.get());
            return ent;
        } else {
           throw new ResourceNotFoundException("no hotel media found with id: " + hotelMediaEntity.getId());
        }
    }

    @Override
    public void deleteHotelMediaById(Long id) throws ResourceNotFoundException {
        Optional<HotelMediaEntity> optHotelMedia = hotelMediaRepo.findById(id);
        if(optHotelMedia.isPresent()) {
            hotelMediaRepo.delete(optHotelMedia.get());
        } else {
           throw new ResourceNotFoundException("no hotel media found with id: " + id);
        }
    }
}

