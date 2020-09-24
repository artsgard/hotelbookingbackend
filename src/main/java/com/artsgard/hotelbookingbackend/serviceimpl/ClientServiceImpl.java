package com.artsgard.hotelbookingbackend.serviceimpl;

import com.artsgard.hotelbookingbackend.DTO.ClientDTO;
import com.artsgard.hotelbookingbackend.service.MapperService;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import com.artsgard.hotelbookingbackend.entity.ClientEntity;
import com.artsgard.hotelbookingbackend.repository.ClientRepository;
import com.artsgard.hotelbookingbackend.service.ClientService;
import java.util.ArrayList;
import java.util.Optional;
import com.artsgard.hotelbookingbackend.repository.BookerRepository;
import java.util.Date;

/**
 *
 * @author WillemDragstra
 */
@Service
public class ClientServiceImpl implements ClientService {

    org.slf4j.Logger logger;

    @Autowired
    private MapperService mapperService;

    @Autowired
    private BookerRepository flightRepo;
    
    @Autowired
    private ClientRepository clientRepo;

    public ClientServiceImpl() {
        logger = LoggerFactory.getLogger(ClientServiceImpl.class);
    }

    @Override
    public List<ClientDTO> findAllClients() throws ResourceNotFoundException {
        List<ClientEntity> clients = clientRepo.findAll();
        if (clients.isEmpty()) {
            throw new ResourceNotFoundException("no Clients found!");
        } else {
            List<ClientDTO> clientList = new ArrayList();

            for (ClientEntity clnt : clients) {
                ClientDTO clntDTO = mapperService.mapClientEntityToClientDTO(clnt);
                clientList.add(clntDTO);
            }
            return clientList;
        }
    }

    @Override
    public ClientDTO findClientById(Long id) throws ResourceNotFoundException {
        Optional<ClientEntity> optClient = clientRepo.findById(id);
        if(optClient.isPresent()) {
            return mapperService.mapClientEntityToClientDTO(optClient.get());
        } else {
           throw new ResourceNotFoundException("no Client found with id: " + id);
        }
    }

    @Override
    public ClientDTO findClientByUsername(String username) throws ResourceNotFoundException {
        Optional<ClientEntity> optClient = clientRepo.findByUsername(username);
        if(optClient.isPresent()) {
            return mapperService.mapClientEntityToClientDTO(optClient.get());
        } else {
           throw new ResourceNotFoundException("no Client found with username: " + username);
        }
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        ClientEntity ent = mapperService.mapClientDTOToClientEntity(clientDTO);
        ent.setRegisterDate(new Date());
        return mapperService.mapClientEntityToClientDTO( clientRepo.save(ent));
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) throws ResourceNotFoundException {
        Optional<ClientEntity> optClient = clientRepo.findById(clientDTO.getId());
        if(optClient.isPresent()) {
            ClientEntity ent = clientRepo.save(optClient.get());
            return mapperService.mapClientEntityToClientDTO(ent);
        } else {
           throw new ResourceNotFoundException("no Client found with id: " + clientDTO.getId());
        }
    }

    @Override
    public void deleteClientById(Long id) throws ResourceNotFoundException {
        Optional<ClientEntity> optClient = clientRepo.findById(id);
        if(optClient.isPresent()) {
            clientRepo.delete(optClient.get());
        } else {
           throw new ResourceNotFoundException("no Client found with id: " + id);
        }
    }
}
