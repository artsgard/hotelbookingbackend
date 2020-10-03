package com.artsgard.hotelbookingbackend.service;

import com.artsgard.hotelbookingbackend.entity.ClientEntity;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import com.artsgard.hotelbookingbackend.repository.ClientRepository;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource({"classpath:application-test.properties"})

@DataJpaTest
public class BookerServiceDataJpaTest {

    @Autowired
    private ClientRepository clientRepo;
    
    public static final Long NON_EXISTING_ID = 7000L;
    public static final String NON_EXISTING_USERNAME = "SDFSDFSFSDFSDF";
 
    @Test
    public void testGetAllClients() {
        List<ClientEntity> clients = clientRepo.findAll();
        assertThat(clients).isNotEmpty();
        assertThat(clients).hasSize(1);
    }
    
    @Test
    public void testGetAllClients_not_found() {
        clientRepo.deleteAll();
        List<ClientEntity> clients = clientRepo.findAll();
        assertThatExceptionOfType(ResourceNotFoundException.class);
    }

    @Test
    public void testGetClientById() {
        ClientEntity sc = clientRepo.getOne(1L);
        assertThat(sc).isNotNull();
    }
    
    @Test
    public void findClientByIdTest_not_found() {
        ClientEntity sc = clientRepo.getOne(NON_EXISTING_ID);
        assertThatExceptionOfType(ResourceNotFoundException.class);
    }
   
    @Test
    public void testGetClientByUsername() {
    List<ClientEntity> clients = clientRepo.findAll();
        Optional<ClientEntity> optClient = clientRepo.findByUsername("js");
        assertThat(optClient.get().getId()).isNotNull();
        assertThat(optClient.get().getUsername()).isEqualTo("js");
    }
    
    @Test
    public void testGetClientByUsername_not_found() {
    List<ClientEntity> clients = clientRepo.findAll();
        Optional<ClientEntity> optClient = clientRepo.findByUsername(NON_EXISTING_USERNAME);
        assertThatExceptionOfType(ResourceNotFoundException.class);
    }

    @Test
    public void testSaveClient() {
        ClientEntity client = new ClientEntity(null, "username", "password", "first name", "last name", "email", new Date(), null);
        clientRepo.save(client);
        assertThat(client.getId()).isNotNull();
        assertThat(client.getUsername()).isEqualTo("username");
    }

    @Test
    public void testUpdateClient() {
        Optional<ClientEntity> optClient = clientRepo.findById(1L);
        ClientEntity updateClient = optClient.get();
        updateClient.setUsername("js edited");
        ClientEntity updatedClientFromDB = clientRepo.save(updateClient);
        assertThat(optClient.get()).isEqualTo(updatedClientFromDB);
    }
    
    @Test
    public void testUpdateClient_not_found() {
        ClientEntity client = clientRepo.getOne(NON_EXISTING_ID);
        assertThatExceptionOfType(ResourceNotFoundException.class);
    }

    @Test
    public void testDeleteClientById() {
        ClientEntity client = new ClientEntity(null, "username", "password", "first name", "last name", "email", new Date(), null);
        clientRepo.save(client);
        Long id = client.getId();
        clientRepo.deleteById(id);
        Optional<ClientEntity> deletedClient = clientRepo.findById(id);
        assertThat(deletedClient.isPresent()).isFalse();
    }
    
    @Test
    public void testDeleteClientById_not_found() {
        ClientEntity client = clientRepo.getOne(NON_EXISTING_ID);
        assertThatExceptionOfType(ResourceNotFoundException.class);
    }
}
