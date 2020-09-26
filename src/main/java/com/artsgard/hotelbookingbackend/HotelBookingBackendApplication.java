package com.artsgard.hotelbookingbackend;

import javax.servlet.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:application.properties"})
public class HotelBookingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelBookingBackendApplication.class, args);
    }
    
    @Bean
    public Filter CORSFilter() {
        System.out.println("<<<<<<<<<< CORS filter loaded");
        return new CORSFilter();
    }
}
