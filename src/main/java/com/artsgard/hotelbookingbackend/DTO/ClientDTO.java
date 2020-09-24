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
 *  client": {
        "username": "js",
        "password": "secret",
        "firstName": "Johann Sebastian",
        "lastName": "Bach",
        "email": "jsbach@gmail.com",
        "registerDate": "2020-09-24T08:56:10.655+0000",
        "clientBookings": null
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
