package com.artsgard.hotelbookingbackend.controller;

import com.artsgard.hotelbookingbackend.DTO.HotelDTO;
import com.artsgard.hotelbookingbackend.DTO.HotelMediaDTO;
import com.artsgard.hotelbookingbackend.mock.MockData;
import com.artsgard.hotelbookingbackend.service.MapperService;
import com.artsgard.hotelbookingbackend.serviceimpl.HotelMediaServiceImpl;
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
public class HotelMediaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HotelMediaServiceImpl hotelMediaService;

    @InjectMocks
    HotelMediaController hotelMediaController;

    private JacksonTester<HotelMediaDTO> jsonHotelMedia;
    private JacksonTester<List<HotelMediaDTO>> jsonHotelMedias;
    private HotelMediaDTO hotelMediaDTO;
    private List<HotelMediaDTO> hotelMediasDTO;

    private final MapperService mapperService = new MapperServiceImpl();

    @BeforeEach
    public void setup() {
        
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(hotelMediaController).build();

        hotelMediaDTO = mapperService.mapHotelMediaEntityToHotelMediaDTO(MockData.generateHotelMedia());
        hotelMediasDTO = new ArrayList();
        MockData.generateHotelMedias().forEach((sci) -> {
            hotelMediasDTO.add(mapperService.mapHotelMediaEntityToHotelMediaDTO(sci));
        });
    }

    @Test
    public void testFindAllHotelMedias() throws Exception {

        given(hotelMediaService.getAllHotelMedias()).willReturn(hotelMediasDTO);

        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.get("/hotelMedia"))
                .andExpect((content()
                .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonHotelMedias.write(hotelMediasDTO).getJson()
        );
    }

    @Test
    public void testFindHotelMediaById() throws Exception {
        given(hotelMediaService.getHotelMediaById(1L)).willReturn(hotelMediaDTO);

        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.get("/hotelMedia/1"))
                .andExpect((content()
                .contentType(MediaType.APPLICATION_JSON_VALUE)))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonHotelMedia.write(hotelMediaDTO).getJson()
        );
    }

    @Test
    public void testSaveHotelMedia() throws Exception {
        given(hotelMediaService.saveHotelMedia(any(HotelMediaDTO.class))).willReturn(hotelMediaDTO);
        
        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.post("/hotelMedia/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)      
                .content(jsonHotelMedia.write(hotelMediaDTO)
                .getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonHotelMedia.write(hotelMediaDTO).getJson());
    }

    @Test
    public void testUpdatehotelMedia() throws Exception {
        hotelMediaDTO.setHotel(new HotelDTO());
        given(hotelMediaService.updateHotelMedia(any(HotelMediaDTO.class))).willReturn(hotelMediaDTO);
        
        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders.put("/hotelMedia/")
                .contentType(MediaType.APPLICATION_JSON)
            .content(jsonHotelMedia.write(hotelMediaDTO)
                .getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonHotelMedia.write(hotelMediaDTO).getJson());
    }
    
    @Test
    public void testDeleteHotelMedia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/hotelMedia/{id}", "1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
