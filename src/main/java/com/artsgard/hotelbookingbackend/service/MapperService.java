package com.artsgard.hotelbookingbackend.service;

import com.artsgard.hotelbookingbackend.DTO.ClientDTO;
import com.artsgard.hotelbookingbackend.DTO.BookerDTO;
import com.artsgard.hotelbookingbackend.entity.ClientEntity;
import com.artsgard.hotelbookingbackend.entity.BookerEntity;
import org.springframework.stereotype.Service;

@Service
public interface MapperService {
    BookerEntity mapBookerDTOToBookerEntity(BookerDTO dto);
    BookerDTO mapBookerEntityToBookerDTO(BookerEntity ent);

    ClientDTO mapClientEntityToClientDTO(ClientEntity dto);
    ClientEntity mapClientDTOToClientEntity(ClientDTO ent);
}
