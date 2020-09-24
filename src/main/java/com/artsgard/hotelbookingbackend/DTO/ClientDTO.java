package com.artsgard.hotelbookingbackend.DTO;

import com.artsgard.hotelbookingbackend.entity.BookerEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author WillemDragstra
 * 
 *  {
        "username": "kj",
        "password": "secret",
        "firstName": "Karst",
        "lastName": "Dehollander",
        "email": "kdehollander@gmail.com"
  } 
 * 
 */
@Data
@NoArgsConstructor
public class ClientDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String firstName;

    private String lastName;

    @NotNull
    private String email;

    private Date registerDate;
    
    private List<BookerEntity> clientBookings;
}
