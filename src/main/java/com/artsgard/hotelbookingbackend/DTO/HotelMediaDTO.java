package com.artsgard.hotelbookingbackend.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author artsgard
 * https://www.concretepage.com/jackson-api/jackson-jsonignore-jsonignoreproperties-and-jsonignoretype
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
    @JsonIgnoreProperties({"hotelMedias"})
    private HotelDTO hotel;
}
