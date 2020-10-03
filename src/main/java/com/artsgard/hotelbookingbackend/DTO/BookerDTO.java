package com.artsgard.hotelbookingbackend.DTO;

import com.artsgard.hotelbookingbackend.entity.BookerEntity.RoomType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author WillemDragstra
 * 
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookerDTO implements Serializable {
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
    @JsonIgnoreProperties("clientBookings")
    //@JsonIgnore
    private ClientDTO client;
    
    @NotNull
    private HotelDTO hotel;

}
