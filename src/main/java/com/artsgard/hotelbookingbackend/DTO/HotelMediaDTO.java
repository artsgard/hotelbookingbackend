/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artsgard.hotelbookingbackend.DTO;

import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class HotelMediaDTO {
  
    private Long id;
    
    @NotNull
    private String title;

    @NotNull
    private String link;
    
    private String description;
    
    @NotNull
    @JsonIgnore
    private HotelEntity hotel;
}
