package com.artsgard.hotelbookingbackend.service;

import com.artsgard.hotelbookingbackend.DTO.ClientDTO;
import com.artsgard.hotelbookingbackend.entity.ClientEntity;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import com.artsgard.hotelbookingbackend.repository.ClientRepository;
import com.artsgard.hotelbookingbackend.serviceimpl.ClientServiceImpl;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClientServiceMockitoTest {

    @Mock
    private ClientRepository clientRepo;

    @InjectMocks
    ClientServiceImpl clientService;

    @Mock
    private MapperService mapperService;

    private ClientEntity clientModelMock1;
    private ClientEntity clientModelMock2;
    private ClientDTO clientDTOMock1;
    private ClientDTO clientDTOMock2;
    private List<ClientEntity> clientModelListMock;
    public static final Long NON_EXISTING_ID = 7000L;
    public static final Long EXISTING_ID = 1L;
    public static final String EXISTING_USERNAME = "username";
    public static final String NON_EXISTING_USERNAME = "SDFSDFSFSDFSDF";

    @BeforeEach
    public void setup() {
        
    }

    @Test
    public void testFindAllClients() {
        given(clientRepo.findAll()).willReturn(clientModelListMock);
        given(mapperService.mapClientEntityToClientDTO(any(ClientEntity.class))).willReturn(clientDTOMock1);
        List<ClientDTO> list = clientService.findAllClients();
        assertThat(list).isNotEmpty().hasSize(2);
    }

    @Test
    public void testFindAllClients_not_found() {
        List<ClientEntity> emptyList = new ArrayList();
        given(clientRepo.findAll()).willReturn(emptyList);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            clientService.findAllClients();
        });
    }

    @Test
    public void testFindClientById() {
        clientModelMock1.setId(EXISTING_ID);
        given(clientRepo.findById(any(Long.class))).willReturn(Optional.of(clientModelMock1));
        given(mapperService.mapClientEntityToClientDTO(any(ClientEntity.class))).willReturn(clientDTOMock1);
        ClientDTO sc = clientService.findClientById(any(Long.class));
        assertThat(sc).isNotNull();
        assertThat(sc.getUsername()).isEqualTo(clientModelMock1.getUsername());
    }

    @Test
    public void testFindClientById_not_found() {
        given(clientRepo.findById(any(Long.class))).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            clientService.findClientById(any(Long.class));
        });
    }

    @Test
    public void testFindClientByUsername() {
        clientModelMock1.setId(EXISTING_ID);
        given(clientRepo.findByUsername(EXISTING_USERNAME)).willReturn(Optional.of(clientModelMock1));
        given(mapperService.mapClientEntityToClientDTO(any(ClientEntity.class))).willReturn(clientDTOMock1);
        ClientDTO sc = clientService.findClientByUsername(EXISTING_USERNAME);
        assertThat(sc).isNotNull();
        assertThat(sc.getUsername()).isEqualTo(clientModelMock1.getUsername());
    }

    @Test
    public void testFindClientByUsername_not_found() {
        given(clientRepo.findByUsername(NON_EXISTING_USERNAME)).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            clientService.findClientByUsername(NON_EXISTING_USERNAME);
        });
    }

    @Test
    public void testSaveClient() {
        clientModelMock1.setId(EXISTING_ID);
        given(clientRepo.save(clientModelMock1)).willReturn(clientModelMock1);
        given(mapperService.mapClientDTOToClientEntity(any(ClientDTO.class))).willReturn(clientModelMock1);
        ClientDTO sc = clientService.saveClient(clientDTOMock1);
        assertThat(sc).isNotNull(); // why is this null!!!!!
    }

    @Test
    public void testUpdateClient() {
        clientDTOMock1.setId(EXISTING_ID);
        clientModelMock1.setId(EXISTING_ID);
        given(clientRepo.findById(any(Long.class))).willReturn(Optional.of(clientModelMock1));
        given(clientRepo.save(clientModelMock1)).willReturn(clientModelMock1);
        given(mapperService.mapClientDTOToClientEntity(any(ClientDTO.class))).willReturn(clientModelMock1);
        
        ClientDTO sc = clientService.updateClient(clientDTOMock1);
        assertThat(sc).isNotNull(); // why is this null!!!!!
    }

    @Test
    public void testUpdateClient_not_found() {
        given(clientRepo.findById(any(Long.class))).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            clientDTOMock1.setId(any(Long.class));
            clientService.updateClient(clientDTOMock1);
        });
    }

    @Test
    public void testDeleteClientById() {
        clientRepo.deleteById(EXISTING_ID);
        verify(clientRepo, times(1)).deleteById(eq(EXISTING_ID));
    }

    @Test
    public void testDeleteClientById_not_found() {
        given(clientRepo.findById(any(Long.class))).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            clientService.deleteClientById(any(Long.class));
        });
    }

}
