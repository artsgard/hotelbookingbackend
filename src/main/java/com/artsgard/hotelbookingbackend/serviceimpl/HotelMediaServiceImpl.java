package com.artsgard.hotelbookingbackend.serviceimpl;

import com.artsgard.hotelbookingbackend.DTO.HotelMediaDTO;
import com.artsgard.hotelbookingbackend.entity.HotelMediaEntity;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import com.artsgard.hotelbookingbackend.repository.HotelMediaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artsgard.hotelbookingbackend.service.HotelMediaService;
import com.artsgard.hotelbookingbackend.service.MapperService;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class HotelMediaServiceImpl implements HotelMediaService {
    
    @Autowired
    HotelMediaRepository hotelMediaRepo;
    
    @Autowired
    private MapperService mapperService;

    @Override
    public List<HotelMediaDTO> getAllHotelMedias() {
        List<HotelMediaEntity> hotelMedias = hotelMediaRepo.findAll();
        List<HotelMediaDTO> list = new ArrayList();
        if(hotelMedias != null) {
            for(HotelMediaEntity htlm: hotelMedias) {
                HotelMediaDTO val = mapperService.mapHotelMediaEntityToHotelMediaDTO(htlm);
                list.add(val);
            }
        } else {
            throw new ResourceNotFoundException("no Hotel Medias found!");
        }
        return list;
    }

    @Override
    public HotelMediaDTO getHotelMediaById(Long id) {
        Optional<HotelMediaEntity> opt = hotelMediaRepo.findById(id);
        if(opt.isPresent()) {
            return mapperService.mapHotelMediaEntityToHotelMediaDTO(opt.get());
        } else {
           throw new ResourceNotFoundException("no Hotel Media found with id " + id);
        }
    }
    
    @Override
    public HotelMediaDTO saveHotelMedia(HotelMediaDTO dto) {
        HotelMediaEntity ent = mapperService.mapHotelMediaDTOToHotelMediaEntity(dto);
        return mapperService.mapHotelMediaEntityToHotelMediaDTO(hotelMediaRepo.save(ent));
    }

    @Override
    public HotelMediaDTO updateHotelMedia(HotelMediaDTO dto) throws ResourceNotFoundException {
        Optional<HotelMediaEntity> opt = hotelMediaRepo.findById(dto.getId());
        if(opt.isPresent()) {
            return mapperService.mapHotelMediaEntityToHotelMediaDTO(hotelMediaRepo.save(opt.get()));
        } else {
           throw new ResourceNotFoundException("no hotel media found with id: " + dto.getId());
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

