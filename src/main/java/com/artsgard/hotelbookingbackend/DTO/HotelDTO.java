/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artsgard.hotelbookingbackend.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author artsgard
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
    
    private Long id;

    @NotNull
    private String name;
    
    private String description;
    
    @NotNull
    private String city;
    
    @NotNull
    private String street;
    
    @NotNull
    private Integer phone;
     
    private String email;
    
    @NotNull
    private BigDecimal singleRoom;
    
    @NotNull
    private BigDecimal doubleRoom;
    
    @NotNull
    private BigDecimal tripleRoom;
    
    @NotNull
    private BigDecimal breakfastIncluded;
    
    //private List<BookerDTO> hotelBookings;
    
    private List<HotelMediaDTO> hotelMedias;
}
