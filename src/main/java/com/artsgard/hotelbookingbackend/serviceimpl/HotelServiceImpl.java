package com.artsgard.hotelbookingbackend.serviceimpl;

import com.artsgard.hotelbookingbackend.entity.ClientEntity;
import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import com.artsgard.hotelbookingbackend.entity.HotelMediaEntity;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import com.artsgard.hotelbookingbackend.repository.HotelMediaRepository;
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

    @Autowired
    HotelMediaRepository hotelMediaRepo;

    @Override
    public List<HotelEntity> getAllHotels() {
        List<HotelEntity> hotels = hotelRepo.findAll();
        return hotelRepo.findAll();
    }

    @Override
    public HotelEntity getHotelById(Long id) {
        Optional<HotelEntity> optHotel = hotelRepo.findById(id);
        if (optHotel.isPresent()) {
            return optHotel.get();
        } else {
            throw new ResourceNotFoundException("no hotels found!");
        }
    }

    @Override
    public HotelEntity saveHotel(HotelEntity hotelEntity) {
        HotelEntity hotel = hotelRepo.save(hotelEntity);
        List<HotelMediaEntity> medias = hotelEntity.getHotelMedias();
        medias.stream().map(media -> {
            media.setHotel(hotel);
            return media;
        }).forEachOrdered(media -> {
            hotelMediaRepo.save(media);
        });

        return hotel;
    }

    @Override
    public HotelEntity updateHotel(HotelEntity hotelEntity) throws ResourceNotFoundException {
        Optional<HotelEntity> optHotel = hotelRepo.findById(hotelEntity.getId());
        HotelEntity repoHotel = optHotel.get();
        if (optHotel.isPresent()) {
            if (hotelEntity.getName() == null) {
                hotelEntity.setName(repoHotel.getName());
            }
            if (hotelEntity.getCity() == null) {
                hotelEntity.setCity(repoHotel.getName());
            }
            if (hotelEntity.getStreet() == null) {
                hotelEntity.setStreet(repoHotel.getStreet());
            }
            if (hotelEntity.getPhone() == null) {
                hotelEntity.setPhone(repoHotel.getPhone());
            }
            if (hotelEntity.getEmail() == null) {
                hotelEntity.setEmail(repoHotel.getEmail());
            }
            if (hotelEntity.getBreakfastIncluded() == null) {
                hotelEntity.setBreakfastIncluded(repoHotel.getBreakfastIncluded());
            }
            if (hotelEntity.getSingleRoom() == null) {
                hotelEntity.setSingleRoom(repoHotel.getSingleRoom());
            }
            if (hotelEntity.getDoubleRoom() == null) {
                hotelEntity.setDoubleRoom(repoHotel.getDoubleRoom());
            }
            if (hotelEntity.getTripleRoom() == null) {
                hotelEntity.setTripleRoom(repoHotel.getTripleRoom());
            }
            if (hotelEntity.getDescription() == null) {
                hotelEntity.setDescription(repoHotel.getDescription());
            }
            HotelEntity ent = hotelRepo.save(optHotel.get());
            List<HotelMediaEntity> medias = hotelEntity.getHotelMedias();
            medias.stream().map(media -> {
                media.setHotel(ent);
                return media;
            }).forEachOrdered(media -> {
                hotelMediaRepo.save(media);
            });

            return ent;
        } else {
            throw new ResourceNotFoundException("no hotel found with id: " + hotelEntity.getId());
        }
    }

    @Override
    public void deleteHotelById(Long id) throws ResourceNotFoundException {
        Optional<HotelEntity> optHotel = hotelRepo.findById(id);
        if (optHotel.isPresent()) {
            hotelRepo.delete(optHotel.get());
        } else {
            throw new ResourceNotFoundException("no hotel found with id: " + id);
        }
    }
}
