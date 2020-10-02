package com.artsgard.hotelbookingbackend.controller;

import com.artsgard.hotelbookingbackend.DTO.BookerDTO;
import com.artsgard.hotelbookingbackend.controller.BookerController;
import com.artsgard.hotelbookingbackend.entity.ClientEntity;
import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import com.artsgard.hotelbookingbackend.entity.HotelMediaEntity;
import com.artsgard.hotelbookingbackend.mock.MockData;
import com.artsgard.hotelbookingbackend.service.MapperService;
import com.artsgard.hotelbookingbackend.serviceimpl.BookerServiceImpl;
import com.artsgard.hotelbookingbackend.serviceimpl.ClientServiceImpl;
import com.artsgard.hotelbookingbackend.serviceimpl.HotelMediaServiceImpl;
import com.artsgard.hotelbookingbackend.serviceimpl.HotelServiceImpl;
import com.artsgard.hotelbookingbackend.serviceimpl.MapperServiceImpl;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(BookerController.class)
public class BookerControllerSpringStandaloneContextTest {
   /* 
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientServiceImpl clientService;
    
    @MockBean
    private HotelServiceImpl hotelService;
    
    @MockBean
    private HotelMediaServiceImpl hotelMediaService;
     
    @MockBean
    private BookerServiceImpl bookerService;

    @InjectMocks
    BookerController bookerController;
    
    @Autowired
    private JacksonTester <ClientEntity> jsonClient;
     
    @Autowired
    private JacksonTester<List<ClientEntity>> jsonClients;
    
    @Autowired
    private JacksonTester<BookerDTO> jsonBooker;
    
    @Autowired
    private JacksonTester<List<BookerDTO>> jsonBookers;
    
    @Autowired
    private JacksonTester <HotelEntity> jsonHotel;
     
    @Autowired
    private JacksonTester<List<HotelEntity>> jsonHotels;
    
    @Autowired
    private JacksonTester <HotelMediaEntity> jsonHotelMedia;
     
    @Autowired
    private JacksonTester<List<HotelMediaEntity>> jsonHotelsMedia;
    
    private BookerDTO bookerMock;
    private List<BookerDTO> bookersMock;

    private final MapperService mapperService = new MapperServiceImpl();

    @BeforeEach
    public void setup() {
        bookerMock = mapperService.mapBookerModelToBookerDTO(MockData.generateBooker());
        bookersMock = new ArrayList();
        
        MockData.generateBookers().forEach((sci) -> {
            bookersMock.add(mapperService.mapBookerModelToBookerDTO(sci));
        });
        
    }
    
    @Test
    void injectedComponentsAreNotNull() {
        assertThat(mockMvc).isNotNull();
        //assertThat(jsonBookers).isNotNull();
    }

    @Test
    public void testFindAllBookers() throws Exception {

        given(bookerService.findAllBookings()).willReturn(bookersMock);

        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.get("/booker"))
                .andExpect((content()
                .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonBookers.write(bookersMock).getJson()
        );
    }

    @Test
    public void testFindBookerById() throws Exception {
        given(bookerService.findBookingById(1L)).willReturn(bookerMock);

        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.get("/booker/1"))
                .andExpect((content()
                .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonBooker.write(bookerMock).getJson()
        );
    }


    @Test
    public void testSaveBooker() throws Exception {
       
        given(bookerService.saveBooker(newBooker)).willReturn(newBooker);
        
        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.post("/booker/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)      
                .content(jsonBooker.write(newBooker)
                .getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
         assertThat(response.getContentAsString()).isEqualTo(
                jsonBooker.write(newBooker).getJson());
    }

    @Test
    public void testUpdatebooker() throws Exception {
        BookerDTO editBooker = new BookerDTO(1L, "js edited", "secret", "Johann Sebastian", "Bach", "jsbach@gmail.com", true, new <LanguageModel>ArrayList(), new <AddressDTO>ArrayList());
        editBooker.setRegisterDate(new Timestamp(1479250540110L));
        editBooker.setLastCheckinDate(new Timestamp(1479250540110L));
        given(bookerService.updateBooker(editBooker)).willReturn(editBooker);
        
        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.put("/booker/")
                .contentType(MediaType.APPLICATION_JSON)
            .content(jsonBooker.write(editBooker)
                .getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonBooker.write(editBooker).getJson());
    }

    @Test
    public void testDeleteBooker() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/booker/{id}", "1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
*/
}