package com.artsgard.hotelbookingbackend.service;

import com.artsgard.hotelbookingbackend.DTO.ClientDTO;
import com.artsgard.hotelbookingbackend.DTO.BookerDTO;
import com.artsgard.hotelbookingbackend.DTO.HotelDTO;
import com.artsgard.hotelbookingbackend.DTO.HotelMediaDTO;
import com.artsgard.hotelbookingbackend.entity.ClientEntity;
import com.artsgard.hotelbookingbackend.entity.BookerEntity;
import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import com.artsgard.hotelbookingbackend.entity.HotelMediaEntity;
import org.springframework.stereotype.Service;

@Service
public interface MapperService {
    BookerEntity mapBookerDTOToBookerEntity(BookerDTO dto);
    BookerDTO mapBookerEntityToBookerDTO(BookerEntity ent);

    ClientDTO mapClientEntityToClientDTO(ClientEntity dto);
    ClientEntity mapClientDTOToClientEntity(ClientDTO ent);
    
    HotelDTO mapHotelEntityToHotelDTO(HotelEntity dto);
    HotelEntity mapHotelDTOToHotelEntity(HotelDTO ent);
    
    HotelMediaDTO mapHotelMediaEntityToHotelMediaDTO(HotelMediaEntity dto);
    HotelMediaEntity mapHotelMediaDTOToHotelMediaEntity(HotelMediaDTO ent);
}
