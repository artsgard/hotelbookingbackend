package com.artsgard.hotelbookingbackend.service;

import com.artsgard.hotelbookingbackend.entity.BookerEntity;
import com.artsgard.hotelbookingbackend.entity.BookerEntity.RoomType;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import com.artsgard.hotelbookingbackend.mock.MockData;
import com.artsgard.hotelbookingbackend.repository.BookerRepository;
import com.artsgard.hotelbookingbackend.serviceimpl.MapperServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource({"classpath:application-test.properties"})

@DataJpaTest
public class BookerServiceDataJpaTest {

    @Autowired
    private BookerRepository bookerRepo;
    
    public static final Long NON_EXISTING_ID = 7000L;
    public static final String NON_EXISTING_USERNAME = "SDFSDFSFSDFSDF";
    
    private final MapperService mapperService = new MapperServiceImpl();
    
    @BeforeEach
    public void setup() {
        BookerEntity booker1 = new BookerEntity(null, new Date(), new Date(),
                BookerEntity.RoomType.DOUBLE, true, new Date(), 3, new BigDecimal("100.00"),
                MockData.generateClient(),
                MockData.generateHotel());
         BookerEntity booker2 = new BookerEntity(null, new Date(), new Date(),
                BookerEntity.RoomType.DOUBLE, false, new Date(), 3, new BigDecimal("200.00"),
                MockData.generateClient(),
                MockData.generateHotel());
         List<BookerEntity> list = new ArrayList();
         list.add(booker1);
         list.add(booker2);
         bookerRepo.saveAll(list);
    }
 
    @Test
    public void testGetAllBookers() {
        List<BookerEntity> bookers = bookerRepo.findAll();
        assertThat(bookers).isNotEmpty();
        assertThat(bookers).hasSize(2);
    }
    
    @Test
    public void testGetAllBookers_not_found() {
        bookerRepo.deleteAll();
        List<BookerEntity> bookers = bookerRepo.findAll();
        assertThatExceptionOfType(ResourceNotFoundException.class);
    }

    @Test
    public void testGetBookerById() {
        BookerEntity sc = bookerRepo.getOne(1L);
        assertThat(sc).isNotNull();
    }
    
    @Test
    public void findBookerByIdTest_not_found() {
        BookerEntity sc = bookerRepo.getOne(NON_EXISTING_ID);
        assertThatExceptionOfType(ResourceNotFoundException.class);
    }
   

    @Test
    public void testSaveBooker() {
        BookerEntity booker = new BookerEntity(null, new Date(), new Date(),
                BookerEntity.RoomType.SINGLE, true, new Date(), 7, new BigDecimal("100.00"),
                MockData.generateClient(),
                MockData.generateHotel());
        bookerRepo.save(booker);
        assertThat(booker.getId()).isNotNull();
        assertThat(booker.getRoomType()).isEqualTo(RoomType.SINGLE);
    }

    @Test
    public void testUpdateBooker() {
         BookerEntity booker = new BookerEntity(null, new Date(), new Date(),
                BookerEntity.RoomType.SINGLE, true, new Date(), 7, new BigDecimal("100.00"),
                MockData.generateClient(),
                MockData.generateHotel());
         bookerRepo.save(booker);
        Optional<BookerEntity> optBooker = bookerRepo.findById(booker.getId());
        BookerEntity updateBooker = optBooker.get();
        updateBooker.setNights(7);
        BookerEntity updatedBookerFromDB = bookerRepo.save(updateBooker);
        assertThat(optBooker.get()).isEqualTo(updatedBookerFromDB);
    }
    
    @Test
    public void testUpdateBooker_not_found() {
        BookerEntity booker = bookerRepo.getOne(NON_EXISTING_ID);
        assertThatExceptionOfType(ResourceNotFoundException.class);
    }

    @Test
    public void testDeleteBookerById() {
        BookerEntity booker = new BookerEntity(null, new Date(), new Date(),
                BookerEntity.RoomType.SINGLE, true, new Date(), 12, new BigDecimal("100.00"),
                MockData.generateClient(),
                MockData.generateHotel());
        bookerRepo.save(booker);
        Long id = booker.getId();
        bookerRepo.deleteById(id);
        Optional<BookerEntity> deletedBooker = bookerRepo.findById(id);
        assertThat(deletedBooker.isPresent()).isFalse();
    }
    
    @Test
    public void testDeleteBookerById_not_found() {
        BookerEntity booker = bookerRepo.getOne(NON_EXISTING_ID);
        assertThatExceptionOfType(ResourceNotFoundException.class);
    }
}
