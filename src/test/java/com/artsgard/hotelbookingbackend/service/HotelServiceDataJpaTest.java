package com.artsgard.hotelbookingbackend.service;

import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import com.artsgard.hotelbookingbackend.entity.HotelMediaEntity;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import com.artsgard.hotelbookingbackend.repository.HotelRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class HotelServiceDataJpaTest {

    @Autowired
    private HotelRepository hotelRepo;
    
    public static final Long NON_EXISTING_ID = 7000L;
    public static final String NON_EXISTING_USERNAME = "SDFSDFSFSDFSDF";
 
    @Test
    public void testGetAllHotels() {
        List<HotelEntity> hotels = hotelRepo.findAll();
        hotels.size();
        assertThat(hotels).isNotEmpty();
        assertThat(hotels).hasSize(1);
    }
    
    @Test
    public void testGetAllHotels_not_found() {
        hotelRepo.deleteAll();
        List<HotelEntity> hotels = hotelRepo.findAll();
        assertThatExceptionOfType(ResourceNotFoundException.class);
    }

    @Test
    public void testGetHotelById() {
        HotelEntity sc = hotelRepo.getOne(1L);
        assertThat(sc).isNotNull();
    }
    
    @Test
    public void findHotelByIdTest_not_found() {
        HotelEntity sc = hotelRepo.getOne(NON_EXISTING_ID);
        assertThatExceptionOfType(ResourceNotFoundException.class);
    }

    @Test
    public void testSaveHotel() {
        HotelMediaEntity media = new HotelMediaEntity(1L, "name", "link", "description", null);
        List<HotelMediaEntity> list = new ArrayList();
        list.add(media);
        HotelEntity hotel = new HotelEntity(null, "name", "description", "city", "street", 111111, 
                "email", new BigDecimal("100.00"), new BigDecimal("100.00"), 
                new BigDecimal("100.00"), new BigDecimal("100.00"), null);
        hotelRepo.save(hotel);
        assertThat(hotel.getId()).isNotNull();
        assertThat(hotel.getName()).isEqualTo("name");
    }

    @Test
    public void testUpdateHotel() {
        Optional<HotelEntity> optHotel = hotelRepo.findById(1L);
        HotelEntity updateHotel = optHotel.get();
        updateHotel.setName("js edited");
        HotelEntity updatedHotelFromDB = hotelRepo.save(updateHotel);
        assertThat(optHotel.get()).isEqualTo(updatedHotelFromDB);
    }
    
    @Test
    public void testUpdateHotel_not_found() {
        HotelEntity hotel = hotelRepo.getOne(NON_EXISTING_ID);
        assertThatExceptionOfType(ResourceNotFoundException.class);
    }

    @Test
    public void testDeleteHotelById() {
        HotelMediaEntity media = new HotelMediaEntity(1L, "name", "link", "description", null);
        List<HotelMediaEntity> list = new ArrayList();
        list.add(media);
        HotelEntity hotel = new HotelEntity(null, "name", "description", "city", "street", 111111, 
                "email", new BigDecimal("100.00"), new BigDecimal("100.00"), 
                new BigDecimal("100.00"), new BigDecimal("100.00"), null);
        hotelRepo.save(hotel);
        Long id = hotel.getId();
        hotelRepo.deleteById(id);
        Optional<HotelEntity> deletedHotel = hotelRepo.findById(id);
        assertThat(deletedHotel.isPresent()).isFalse();
    }
    
    @Test
    public void testDeleteHotelById_not_found() {
        HotelEntity hotel = hotelRepo.getOne(NON_EXISTING_ID);
        assertThatExceptionOfType(ResourceNotFoundException.class);
    }
}
