package com.artsgard.hotelbookingbackend.service;

import com.artsgard.hotelbookingbackend.DTO.ClientDTO;
import com.artsgard.hotelbookingbackend.DTO.BookerDTO;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author artsgard
 */
@Service
public interface ClientService  {
    List<ClientDTO> findAllClients() throws ResourceNotFoundException;
    ClientDTO findClientById(Long id) throws ResourceNotFoundException;
    ClientDTO findClientByUsername(String username) throws ResourceNotFoundException; 
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO updateClient(ClientDTO clientDTO) throws ResourceNotFoundException;
    void deleteClientById(Long id) throws ResourceNotFoundException;
}
