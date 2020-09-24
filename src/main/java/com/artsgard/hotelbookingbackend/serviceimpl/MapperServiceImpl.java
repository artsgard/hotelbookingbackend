package com.artsgard.hotelbookingbackend.serviceimpl;

import com.artsgard.hotelbookingbackend.DTO.ClientDTO;
import com.artsgard.hotelbookingbackend.service.MapperService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artsgard.hotelbookingbackend.DTO.BookerDTO;
import com.artsgard.hotelbookingbackend.entity.ClientEntity;
import com.artsgard.hotelbookingbackend.entity.BookerEntity;
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
}
