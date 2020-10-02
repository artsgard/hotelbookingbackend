package com.artsgard.hotelbookingbackend.controller;

import com.artsgard.hotelbookingbackend.DTO.ClientDTO;
import com.artsgard.hotelbookingbackend.DTO.BookerDTO;
import com.artsgard.hotelbookingbackend.service.ClientService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author WillemDragstra
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    org.slf4j.Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ClientDTO>> findAllClients() {
        return new ResponseEntity<>(clientService.findAllClients(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ClientDTO> findClientById(@PathVariable Long id) {
        return new ResponseEntity<>(clientService.findClientById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/username/{username}", produces = "application/json")
    public ResponseEntity<ClientDTO> findClientByUsername(@PathVariable String username) {
        return new ResponseEntity<>(clientService.findClientByUsername(username), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<ClientDTO> saveClient(@Valid @RequestBody ClientDTO clientDTO) {
        ClientDTO client = clientService.saveClient(clientDTO);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
        //return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateClient(@Valid @RequestBody ClientDTO clientDTO) {
        return new ResponseEntity<>(clientService.updateClient(clientDTO), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BookerDTO> deleteClientById(@PathVariable("id") Long id) {
        clientService.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}