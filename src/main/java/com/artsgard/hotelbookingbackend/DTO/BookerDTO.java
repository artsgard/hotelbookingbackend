package com.artsgard.hotelbookingbackend.DTO;

import com.artsgard.hotelbookingbackend.entity.BookerEntity.RoomType;
import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import com.artsgard.hotelbookingbackend.entity.ClientEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author WillemDragstra
 * 
 * {
    "checkInDate": "2020-12-7",
    "checkOutDate": "2021-1-12",
    "rooms": 1,
    "roomType": "DOUBLE",
    "breakfastIncluded": true,
    "client": {
        "id": 1,
        "username": "js",
        "password": "secret",
        "firstName": "Johann Sebastian",
        "lastName": "Bach",
        "email": "jsbach@gmail.com",
        "registerDate": "2020-09-24T08:56:10.655+0000",
        "clientBookings": null
    },
    "hotel": {
        "id": 1,
        "name": "City Hotel",
        "city": "Heerlen",
        "street": "Wannerplein 23",
        "breakfastIncluded": true,
        "description": "Some Nice Hotel",
        "singleRoom": 60.00,
        "doubleRoom": 80.00,
        "tripleRoom": 90.00,
        "hotelMedia": {
            "id": 1,
            "description": "facade",
            "link": "Some-link",
            "title": "City Hotel"
        }
    }
}
 * 
 */
@Data
@NoArgsConstructor
public class BookerDTO implements Serializable {
    
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    private Long id;;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date checkInDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date checkOutDate;

    @NotNull
    private RoomType roomType;
    
    @NotNull
    private Boolean breakfastIncluded;

    private Date bookingDate;

    @NotNull
    private ClientDTO client;
    
    private Integer nights;
    
    private BigDecimal finalPrice;
    
    @NotNull
    private HotelDTO hotel;
}
