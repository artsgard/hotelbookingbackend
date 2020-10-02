package com.artsgard.hotelbookingbackend.DTO;

import com.artsgard.hotelbookingbackend.entity.BookerEntity.RoomType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
*/
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class BookerDTO implements Serializable {

    public BookerDTO(Long id, Date checkInDate, Date checkOutDate, RoomType roomType, Boolean breakfastIncluded, Date bookingDate, Integer nights, BigDecimal finalPrice, HotelDTO hotel, ClientDTO client) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomType = roomType;
        this.breakfastIncluded = breakfastIncluded;
        this.bookingDate = bookingDate;
        this.nights = nights;
        this.finalPrice = finalPrice;
        this.hotel = hotel;
        this.client = client;
    }

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

    private Integer nights;
    
    private BigDecimal finalPrice;
    
    @NotNull
    private HotelDTO hotel;
    
    @NotNull
    @JsonIgnoreProperties("clientBookings")
    //@JsonIgnore
    private ClientDTO client;

}
