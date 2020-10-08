package com.artsgard.hotelbookingbackend.serviceimpl;

import com.artsgard.hotelbookingbackend.DTO.HotelDTO;
import com.artsgard.hotelbookingbackend.DTO.HotelMediaDTO;
import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import com.artsgard.hotelbookingbackend.entity.HotelMediaEntity;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import com.artsgard.hotelbookingbackend.repository.HotelMediaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artsgard.hotelbookingbackend.repository.HotelRepository;
import com.artsgard.hotelbookingbackend.service.HotelService;
import com.artsgard.hotelbookingbackend.service.MapperService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepo;

    @Autowired
    HotelMediaRepository hotelMediaRepo;
    
    @Autowired
    FileLoadService fileService;

    @Autowired
    private MapperService mapperService;

    @Override
    public List<HotelDTO> getAllHotels() {
        List<HotelEntity> hotels = hotelRepo.findAll();
        List<HotelDTO> list = new ArrayList();
        if (hotels != null) {
            for (HotelEntity htlm : hotels) {
                HotelDTO val = mapperService.mapHotelEntityToHotelDTO(htlm);
                list.add(mapperService.mapHotelEntityToHotelDTO(htlm));
            }
        } else {
            throw new ResourceNotFoundException("no Hotels found!");
        }
        return list;
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        Optional<HotelEntity> opt = hotelRepo.findById(id);
        if (opt.isPresent()) {
            return mapperService.mapHotelEntityToHotelDTO(opt.get());
        } else {
            throw new ResourceNotFoundException("no Hotel found with id " + id);
        }
    }

    @Override
    public HotelDTO saveHotel(HotelDTO dto) {
        
        try {
            String dirNameWithScore = dto.getName().replaceAll(" ", "-");
            String[] extArray = dto.getHotelMedias().get(0).getTempFileName().split("\\.");
            String fileExtention = "";
            
            if(extArray.length > 0) {
                fileExtention = "." +  extArray[extArray.length - 1];
            }
            
            changeDirName(dto.getHotelMedias().get(0).getTempDirName(), dirNameWithScore);
            changeFileNameInDir(dto.getHotelMedias().get(0).getTempFileName(), dto.getHotelMedias().get(0).getLink()+ fileExtention, dirNameWithScore);
                     
            HotelEntity hotel = mapperService.mapHotelDTOToHotelEntity(dto);
            hotelRepo.save(hotel);
            
            List<HotelMediaDTO> medias = dto.getHotelMedias();
            List<HotelMediaEntity> list = new ArrayList();
            for (HotelMediaDTO media : medias) {
                HotelMediaEntity ent = mapperService.mapHotelMediaDTOToHotelMediaEntity(media);
                ent.setHotel(hotel);
                hotelMediaRepo.save(ent);
                list.add(ent);
            }
            hotel.setHotelMedias(list);
            return mapperService.mapHotelEntityToHotelDTO(hotel);
        } catch (IOException ex) {
            Logger.getLogger(HotelServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
           return null;
        }
    }

    @Override
    public HotelDTO updateHotel(HotelDTO hotelDTO) throws ResourceNotFoundException {
        Optional<HotelEntity> optHotel = hotelRepo.findById(hotelDTO.getId());
        HotelEntity repoHotel = optHotel.get();
        if (optHotel.isPresent()) {
            if (hotelDTO.getName() == null) {
                hotelDTO.setName(repoHotel.getName());
            }
            if (hotelDTO.getCity() == null) {
                hotelDTO.setCity(repoHotel.getName());
            }
            if (hotelDTO.getStreet() == null) {
                hotelDTO.setStreet(repoHotel.getStreet());
            }
            if (hotelDTO.getPhone() == null) {
                hotelDTO.setPhone(repoHotel.getPhone());
            }
            if (hotelDTO.getEmail() == null) {
                hotelDTO.setEmail(repoHotel.getEmail());
            }
            if (hotelDTO.getBreakfastIncluded() == null) {
                hotelDTO.setBreakfastIncluded(repoHotel.getBreakfastIncluded());
            }
            if (hotelDTO.getSingleRoom() == null) {
                hotelDTO.setSingleRoom(repoHotel.getSingleRoom());
            }
            if (hotelDTO.getDoubleRoom() == null) {
                hotelDTO.setDoubleRoom(repoHotel.getDoubleRoom());
            }
            if (hotelDTO.getTripleRoom() == null) {
                hotelDTO.setTripleRoom(repoHotel.getTripleRoom());
            }
            if (hotelDTO.getDescription() == null) {
                hotelDTO.setDescription(repoHotel.getDescription());
            }

            HotelEntity savedEntity = hotelRepo.save(mapperService.mapHotelDTOToHotelEntity(hotelDTO));

            List<HotelMediaDTO> medias = hotelDTO.getHotelMedias();

            medias.stream().map(dto -> mapperService.mapHotelMediaDTOToHotelMediaEntity(dto)).map(mediaent -> {
                mediaent.setHotel(savedEntity);
                return mediaent;
            }).forEachOrdered(mediaent -> {
                hotelMediaRepo.save(mediaent);
            });

            return mapperService.mapHotelEntityToHotelDTO(savedEntity);
        } else {
            throw new ResourceNotFoundException("no hotel found with id: " + hotelDTO.getId());
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
    
    private void changeDirName(String oldName, String newName) throws IOException {
        fileService.renameDir(oldName, newName);
    }
    
     private void changeFileNameInDir(String oldName, String newName, String dirName) throws IOException {
        fileService.renameFileInDir(oldName, newName, dirName);
    }
}
