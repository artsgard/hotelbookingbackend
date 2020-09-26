package com.artsgard.hotelbookingbackend.serviceimpl;

import com.artsgard.hotelbookingbackend.DTO.BookerDTO;
import com.artsgard.hotelbookingbackend.entity.BookerEntity;
import com.artsgard.hotelbookingbackend.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artsgard.hotelbookingbackend.repository.BookerRepository;
import com.artsgard.hotelbookingbackend.repository.ClientRepository;
import com.artsgard.hotelbookingbackend.repository.HotelMediaRepository;
import com.artsgard.hotelbookingbackend.repository.HotelRepository;
import com.artsgard.hotelbookingbackend.service.BookerService;
import com.artsgard.hotelbookingbackend.service.MapperService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class BookerServiceImpl implements BookerService {

    org.slf4j.Logger logger;

    @Autowired
    BookerRepository bookerRepo;
    
    @Autowired
    HotelRepository hotelRepo;
    
    @Autowired
    HotelMediaRepository hotelMediaRepo;
    
    @Autowired
    ClientRepository clientRepo;
    
    @Autowired
    private MapperService mapperService;

    @Override
    public List<BookerDTO> findAllBookings() throws ResourceNotFoundException {
        List<BookerEntity> bookers = bookerRepo.findAll();
        if (bookers.isEmpty()) {
            throw new ResourceNotFoundException("no Bookers found!");
        } else {
            List<BookerDTO> bookerList = new ArrayList();

            for (BookerEntity clnt : bookers) {
                BookerDTO bkr = mapperService.mapBookerEntityToBookerDTO(clnt);
                bookerList.add(bkr);
            }
            return bookerList;
        }
    }

    @Override
    public BookerDTO findBookingById(Long id) throws ResourceNotFoundException {
        Optional<BookerEntity> optBooker = bookerRepo.findById(id);
        if(optBooker.isPresent()) {
            return mapperService.mapBookerEntityToBookerDTO(optBooker.get());
        } else {
           throw new ResourceNotFoundException("no Booker found with id: " + id);
        }
    }

    @Override
    public BookerDTO saveBooking(BookerDTO bookerDTO) {
        BookerEntity ent = mapperService.mapBookerDTOToBookerEntity(bookerDTO);
        ent.setBookingDate(new Date());
        ent.setFinalPrice(new BigDecimal("200.00"));
        ent.setNights(6);
        return mapperService.mapBookerEntityToBookerDTO(bookerRepo.save(ent));
    }

    @Override
    public BookerDTO updateBooking(BookerDTO bookerDTO) throws ResourceNotFoundException {
        Optional<BookerEntity> optBooker = bookerRepo.findById(bookerDTO.getId());
        if(optBooker.isPresent()) {
            BookerEntity ent = bookerRepo.save(optBooker.get());
            return mapperService.mapBookerEntityToBookerDTO(ent);
        } else {
           throw new ResourceNotFoundException("no Booker found with id: " + bookerDTO.getId());
        }
    }

    @Override
    public void deleteBookingById(Long id) throws ResourceNotFoundException {
        Optional<BookerEntity> optBooker = bookerRepo.findById(id);
        if(optBooker.isPresent()) {
            bookerRepo.delete(optBooker.get());
        } else {
           throw new ResourceNotFoundException("no Booker found with id: " + id);
        }
    }

}
