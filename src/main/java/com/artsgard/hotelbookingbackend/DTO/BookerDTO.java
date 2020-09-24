package com.artsgard.hotelbookingbackend.DTO;

import com.artsgard.hotelbookingbackend.entity.BookerEntity.RoomType;
import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import com.artsgard.hotelbookingbackend.entity.ClientEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
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
        "username": "pbxx",
        "password": "secretxx",
        "firstName": "Pierrexxx",
        "lastName": "Boulezxxx",
        "email": "boulez@gmailxx.com",
        "active": true,
        "socioLanguages": [
                {"id": "1"},
                {"id": "2"}
        ] 
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
    private Integer rooms;

    @NotNull
    private RoomType roomType;
    
    @NotNull
    private Boolean breakfastIncluded;

    private Date bookingDate;

    @NotNull
    private ClientEntity client;
    
    @NotNull
    private HotelEntity hotel;
}
