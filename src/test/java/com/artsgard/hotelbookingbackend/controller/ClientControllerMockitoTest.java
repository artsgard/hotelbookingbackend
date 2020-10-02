package com.artsgard.hotelbookingbackend.controller;

import com.artsgard.hotelbookingbackend.DTO.ClientDTO;
import com.artsgard.hotelbookingbackend.mock.MockData;
import com.artsgard.hotelbookingbackend.service.MapperService;
import com.artsgard.hotelbookingbackend.serviceimpl.ClientServiceImpl;
import com.artsgard.hotelbookingbackend.serviceimpl.MapperServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author WillemDragstra
 */

@ExtendWith(MockitoExtension.class)
public class ClientControllerMockitoTest {

    private MockMvc mockMvc;

    @Mock
    private ClientServiceImpl clientService;

    @InjectMocks
    ClientController clientController;

    private JacksonTester<ClientDTO> jsonClient;
    private JacksonTester<List<ClientDTO>> jsonClients;
    private ClientDTO clientDTO;
    private List<ClientDTO> clientsDTO;

    private final MapperService mapperService = new MapperServiceImpl();

    @BeforeEach
    public void setup() {
        
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();

        clientDTO = mapperService.mapClientEntityToClientDTO(MockData.generateClient());
        clientsDTO = new ArrayList();
        MockData.generateClients().forEach((clt) -> {
            clientsDTO.add(mapperService.mapClientEntityToClientDTO(clt));
        });
    }

    @Test
    public void testFindAllClients() throws Exception {

        given(clientService.findAllClients()).willReturn(clientsDTO);

        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.get("/client"))
                .andExpect((content()
                .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonClients.write(clientsDTO).getJson()
        );
    }

    @Test
    public void testFindClientById() throws Exception {
        given(clientService.findClientById(1L)).willReturn(clientDTO);

        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.get("/client/1"))
                .andExpect((content()
                .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonClient.write(clientDTO).getJson()
        );
    }

    @Test
    public void testFindClientByUsername() throws Exception {
        given(clientService.findClientByUsername("client-username")).willReturn(clientDTO);
        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.get("/client/username/client-username"))
                .andExpect((content()
                .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonClient.write(clientDTO).getJson()
        );
    }

    @Test
    public void testSaveClient() throws Exception {
        given(clientService.saveClient(any(ClientDTO.class))).willReturn(clientDTO);
        
        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.post("/client/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)      
                .content(jsonClient.write(clientDTO)
                .getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonClient.write(clientDTO).getJson());
    }
  

    @Test
    public void testUpdateclient() throws Exception {
        given(clientService.updateClient(any(ClientDTO.class))).willReturn(clientDTO);
        
        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.put("/client/")
                .contentType(MediaType.APPLICATION_JSON)
            .content(jsonClient.write(clientDTO)
                .getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonClient.write(clientDTO).getJson());
    }
  
    @Test
    public void testDeleteClient() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/client/{id}", "1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
