package com.artsgard.hotelbookingbackend.controller;

import com.artsgard.hotelbookingbackend.DTO.HotelDTO;
import com.artsgard.hotelbookingbackend.entity.HotelMediaEntity;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import com.artsgard.hotelbookingbackend.mock.MockData;
import com.artsgard.hotelbookingbackend.service.MapperService;
import com.artsgard.hotelbookingbackend.serviceimpl.HotelServiceImpl;
import com.artsgard.hotelbookingbackend.serviceimpl.MapperServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author artsgard
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureJsonTesters
public class HotelControllerServerTest {

    @MockBean
    private HotelServiceImpl hotelService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JacksonTester<HotelDTO> jsonHotel;

    @Autowired
    private JacksonTester<List<HotelDTO>> jsonHotels;

    private HotelDTO hotel;
    private List<HotelDTO> hotels;

    private final MapperService mapperService = new MapperServiceImpl();

    @BeforeEach
    public void setup() {
        hotel = mapperService.mapHotelEntityToHotelDTO(MockData.generateHotel());
        hotels = new ArrayList();
        MockData.generateHotels().forEach((sci) -> {
            hotels.add(mapperService.mapHotelEntityToHotelDTO(sci));
        });
    }

    @Test
    public void testFindAllHotels() throws Exception {
        given(hotelService.getAllHotels())
                .willReturn(hotels);

        ResponseEntity<HotelDTO[]> hotelResponse = restTemplate
                .getForEntity("/hotel", HotelDTO[].class);

        assertThat(hotelResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(hotelResponse.getBody().equals(hotels));

    }

    @Test
    public void testFindHotelById() throws Exception {
        given(hotelService.getHotelById(1L)).willReturn(hotel);

        ResponseEntity<HotelDTO> hotelResponse = restTemplate.getForEntity("/hotel/1", HotelDTO.class);

        assertThat(hotelResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(hotelResponse.getBody().equals(hotel));
    }

    @Test
    public void testSaveHotel() throws Exception {
        hotel.setHotelMedias(new <HotelMediaEntity>ArrayList());
        ResponseEntity<HotelDTO> hotelResponse = restTemplate.postForEntity("/hotel", hotel, HotelDTO.class);

        assertThat(hotelResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void testUpdatehotel() throws Exception {
        hotel.setHotelMedias(new <HotelMediaEntity>ArrayList());
        given(hotelService.updateHotel(hotel)).willReturn(hotel);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonHotel.write(hotel).getJson(), headers);

        ResponseEntity<String> hotelResponse = restTemplate.exchange("/hotel/", HttpMethod.PUT, entity, String.class);

        assertThat(hotelResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(hotelResponse.getBody().equals(hotel));
    }

    @Test
    public void testDeleteHotel() throws Exception {
        try {
            restTemplate.delete("/hotel/1", HotelDTO.class);
        } catch (HttpClientErrorException ex) {
            String message = ex.getResponseBodyAsString();
            throw new ResourceNotFoundException(message);
        }
    }
}
