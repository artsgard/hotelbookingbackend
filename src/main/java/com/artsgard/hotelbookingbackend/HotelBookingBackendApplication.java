package com.artsgard.hotelbookingbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:application.properties"})
public class HotelBookingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelBookingBackendApplication.class, args);
    }
}
