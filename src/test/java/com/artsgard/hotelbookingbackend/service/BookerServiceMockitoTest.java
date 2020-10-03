package com.artsgard.hotelbookingbackend.service;

import com.artsgard.hotelbookingbackend.DTO.BookerDTO;
import com.artsgard.hotelbookingbackend.entity.BookerEntity;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import com.artsgard.hotelbookingbackend.mock.MockData;
import com.artsgard.hotelbookingbackend.repository.BookerRepository;
import com.artsgard.hotelbookingbackend.serviceimpl.BookerServiceImpl;
import com.artsgard.hotelbookingbackend.serviceimpl.MapperServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
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
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class BookerServiceMockitoTest {

    @Mock
    private BookerRepository bookerRepo;

    @InjectMocks
    BookerServiceImpl bookerService;

    @Mock
    private MapperService mapperService;

    private final MapperService realMapperService = new MapperServiceImpl();

    private BookerEntity bookerEntityMock1;
    private BookerEntity bookerEntityMock2;
    private BookerDTO bookerDTOMock;
    private BookerDTO bookerDTOMock2;
    private List<BookerEntity> bookerEntityListMock;
    public static final Long NON_EXISTING_ID = 7000L;
    public static final Long EXISTING_ID = 1L;
    public static final String EXISTING_USERNAME = "username";
    public static final String NON_EXISTING_USERNAME = "SDFSDFSFSDFSDF";

    @BeforeEach
    public void setup() {
        bookerDTOMock = MockData.generateBooker();
        bookerEntityMock1 = realMapperService.mapBookerDTOToBookerEntity(MockData.generateBooker());
        bookerEntityListMock = new ArrayList();
        List<BookerDTO> list = MockData.generateBookers();
        
        for(BookerDTO dto: list) {
            BookerEntity ent = realMapperService.mapBookerDTOToBookerEntity(dto);
            bookerEntityListMock.add(ent);
        }
    }

    @Test
    public void testFindAllBookers() {
        given(bookerRepo.findAll()).willReturn(bookerEntityListMock);
        given(mapperService.mapBookerEntityToBookerDTO(any(BookerEntity.class))).willReturn(bookerDTOMock);
        List<BookerDTO> list = bookerService.findAllBookings();
        assertThat(list).isNotEmpty().hasSize(3);
    }

    @Test
    public void testFindAllBookers_not_found() {
        List<BookerEntity> emptyList = new ArrayList();
        given(bookerRepo.findAll()).willReturn(emptyList);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bookerService.findAllBookings();
        });
    }

    @Test
    public void testFindBookerById() {
        bookerEntityMock1.setId(EXISTING_ID);
        given(bookerRepo.findById(any(Long.class))).willReturn(Optional.of(bookerEntityMock1));
        given(mapperService.mapBookerEntityToBookerDTO(any(BookerEntity.class))).willReturn(bookerDTOMock);
        BookerDTO sc = bookerService.findBookingById(any(Long.class));
        assertThat(sc).isNotNull();
        assertThat(sc.getRoomType()).isEqualTo(bookerEntityMock1.getRoomType());
        assertThat(sc.getNights()).isEqualTo(bookerEntityMock1.getNights());
    }

    @Test
    public void testFindBookerById_not_found() {
        given(bookerRepo.findById(any(Long.class))).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bookerService.findBookingById(any(Long.class));
        });
    }

    @Test
    public void testSaveBooker() {
        bookerEntityMock1.setId(EXISTING_ID);
        given(bookerRepo.save(any(BookerEntity.class))).willReturn(bookerEntityMock1);
        given(mapperService.mapBookerDTOToBookerEntity(any(BookerDTO.class))).willReturn(bookerEntityMock1);
        given(bookerService.saveBooking(bookerDTOMock)).willReturn(bookerDTOMock);
        BookerDTO sc = bookerService.saveBooking(bookerDTOMock);
        assertThat(sc).isNotNull(); // why is this null!!!!!
    }

    @Test
    public void testUpdateBooker() {
        bookerDTOMock.setId(EXISTING_ID);
        bookerEntityMock1.setId(EXISTING_ID);
        given(bookerRepo.findById(any(Long.class))).willReturn(Optional.of(bookerEntityMock1));
        given(bookerRepo.save(bookerEntityMock1)).willReturn(bookerEntityMock1);
        given(mapperService.mapBookerDTOToBookerEntity(any(BookerDTO.class))).willReturn(bookerEntityMock1);
        //given(bookerService.updateBooking(bookerDTOMock)).willReturn(bookerDTOMock);
        BookerDTO sc = bookerService.updateBooking(bookerDTOMock);
        assertThat(sc).isNotNull(); // why is this null!!!!!
    }

    @Test
    public void testUpdateBooker_not_found() {
        given(bookerRepo.findById(any(Long.class))).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bookerDTOMock.setId(any(Long.class));
            bookerService.updateBooking(bookerDTOMock);
        });
    }

    @Test
    public void testDeleteBookerById() {
        bookerRepo.deleteById(EXISTING_ID);
        verify(bookerRepo, times(1)).deleteById(eq(EXISTING_ID));
    }

    @Test
    public void testDeleteBookerById_not_found() {
        given(bookerRepo.findById(any(Long.class))).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bookerService.deleteBookingById(any(Long.class));
        });
    }

}
