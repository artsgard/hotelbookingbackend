package com.artsgard.hotelbookingbackend.serviceimpl;

import com.artsgard.hotelbookingbackend.DTO.ClientDTO;
import com.artsgard.hotelbookingbackend.service.MapperService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artsgard.hotelbookingbackend.DTO.BookerDTO;
import com.artsgard.hotelbookingbackend.DTO.HotelDTO;
import com.artsgard.hotelbookingbackend.DTO.HotelMediaDTO;
import com.artsgard.hotelbookingbackend.entity.ClientEntity;
import com.artsgard.hotelbookingbackend.entity.BookerEntity;
import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import com.artsgard.hotelbookingbackend.entity.HotelMediaEntity;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.LoggerFactory;
import com.artsgard.hotelbookingbackend.repository.BookerRepository;

/**
 *
 * @author WillemDragstra
 * 
 */
@Service
public class MapperServiceImpl implements MapperService {

    org.slf4j.Logger logger;

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private BookerRepository socioRepository;

    public MapperServiceImpl() {
        this.logger = LoggerFactory.getLogger(MapperServiceImpl.class);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public BookerDTO mapBookerEntityToBookerDTO(BookerEntity ent) {
        if (ent != null) {
            BookerDTO bookerDTO = modelMapper.map(ent, BookerDTO.class);
            return bookerDTO;
        } else {
            return null;
        }
    }

    @Override
    public BookerEntity mapBookerDTOToBookerEntity(BookerDTO dto) {
        if (dto != null) {
            BookerEntity bookerEntity = modelMapper.map(dto, BookerEntity.class);
            return bookerEntity;
        } else {
            return null;
        }
    }
    
    @Override
    public ClientDTO mapClientEntityToClientDTO(ClientEntity ent) {
        if (ent != null) {
            ClientDTO clntDTO= modelMapper.map(ent, ClientDTO.class);
            return clntDTO;
        } else {
            return null;
        }
    }

    @Override
    public ClientEntity mapClientDTOToClientEntity(ClientDTO ent) {
        if (ent != null) {
            ClientEntity clntEntity = modelMapper.map(ent, ClientEntity.class);
            return clntEntity;
        } else {
            return null;
        }
    }

    @Override
    public HotelDTO mapHotelEntityToHotelDTO(HotelEntity ent) {
        if (ent != null) {
            HotelDTO hotelDTO = modelMapper.map(ent, HotelDTO.class);
            return hotelDTO;
        } else {
            return null;
        }
    }

    @Override
    public HotelEntity mapHotelDTOToHotelEntity(HotelDTO dto) {
         if (dto != null) {
            HotelEntity htlEntity = modelMapper.map(dto, HotelEntity.class);
            return htlEntity;
        } else {
            return null;
        }
    }

    @Override
    public HotelMediaDTO mapHotelMediaEntityToHotelMediaDTO(HotelMediaEntity ent) {
        if (ent != null) {
            HotelMediaDTO hotelMediaDTO = modelMapper.map(ent, HotelMediaDTO.class);
            return hotelMediaDTO;
        } else {
            return null;
        }
    }

    @Override
    public HotelMediaEntity mapHotelMediaDTOToHotelMediaEntity(HotelMediaDTO dto) {
         if (dto != null) {
            HotelMediaEntity htlEntity = modelMapper.map(dto, HotelMediaEntity.class);
            return htlEntity;
        } else {
            return null;
        }
    }
}
