package com.artsgard.hotelbookingbackend.mock;

import com.artsgard.hotelbookingbackend.entity.ClientEntity;
import com.artsgard.hotelbookingbackend.entity.HotelEntity;
import com.artsgard.hotelbookingbackend.entity.HotelMediaEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author WillemDragstra
 */
public final class MockData {

    private MockData() {
    }

    public static ClientEntity generateClient() {
        //Timestamp now = new Timestamp(System.currentTimeMillis());
        Date now = new Date();
        ClientEntity client = new ClientEntity(1L, "client-username", "secret", "client-first-name", "client-last-name", "client-email@gmail.com", now, null);
        return client;
    }

    public static List<ClientEntity> generateClients() {
        Date now = new Date();
        ClientEntity client1 = new ClientEntity(1L, "client-username1", "secret1", "client-first-name1", "client-last-name1", "client-email1@gmail.com", now, null);
        ClientEntity client2 = new ClientEntity(2L, "client-username1", "secret2", "client-first-name2", "client-last-name2", "client-email2@gmail.com", now, null);
        ClientEntity client3 = new ClientEntity(3L, "client-username1", "secret3", "client-first-name3", "client-last-name3", "client-email3@gmail.com", now, null);

        List<ClientEntity> clients = new ArrayList();
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        return clients;
    }
    
     public static HotelEntity generateHotel() {
        Date now = new Date();
        HotelMediaEntity media = new HotelMediaEntity(1L, "", "", "", null);
        List<HotelMediaEntity> medias = new ArrayList();
        medias.add(media);
        HotelEntity hotel = new HotelEntity(1L, "hotel-name", "hotel-description", "hotel-city", "hotel-street",
                111111, "hotel-email@gamil.com", new BigDecimal("11.11"), new BigDecimal("11.12"),
                new BigDecimal("11.13"), new BigDecimal("11.14"), medias);
        return hotel;
     }

    public static List<HotelEntity> generateHotels() {
        Date now = new Date();
        HotelEntity hotel1 = new HotelEntity(1L, "hotel-name1", "hotel-description1", "hotel-city1", "hotel-street1",
                111111, "hotel-email1@gamil.com", new BigDecimal("11.11"), new BigDecimal("11.12"),
                new BigDecimal("11.13"), new BigDecimal("11.14"), null);
        HotelMediaEntity hotelMedia1 = new HotelMediaEntity(1L, "media-title1", "media-link1", "media-description1", hotel1);

        HotelEntity hotel2 = new HotelEntity(1L, "hotel-name2", "hotel-description2", "hotel-city2", "hotel-street2",
                222222, "hotel-email2@gamil.com", new BigDecimal("22.21"), new BigDecimal("22.22"),
                new BigDecimal("22.23"), new BigDecimal("22.24"), null);
        HotelMediaEntity hotelMedia2 = new HotelMediaEntity(1L, "media-title2", "media-link2", "media-description2", hotel2);

        HotelEntity hotel3 = new HotelEntity(1L, "hotel-name3", "hotel-description3", "hotel-city3", "hotel-street3",
                111111, "hotel-email3@gamil.com", new BigDecimal("33.31"), new BigDecimal("33.32"),
                new BigDecimal("33.3"), new BigDecimal("33.34"), null);
        HotelMediaEntity hotelMedia3 = new HotelMediaEntity(1L, "media-title3", "media-link3", "media-description3", hotel3);

        List<HotelEntity> hotels = new ArrayList();
        hotels.add(hotel1);
        hotels.add(hotel2);
        hotels.add(hotel3);
        return hotels;
    }
    
     public static HotelMediaEntity generateHotelMedia() {
          HotelMediaEntity media = new HotelMediaEntity(1L, "client-username1", "secret1", "client-first-name1", generateHotel());
          return media;
     }
    
    public static List<HotelMediaEntity> generateHotelMedias() {
        HotelMediaEntity media1 = new HotelMediaEntity(1L, "client-username1", "secret1", "client-first-name1", generateHotel());
        HotelMediaEntity media2 = new HotelMediaEntity(2L, "client-username1", "secret2", "client-first-name2", generateHotel());
        HotelMediaEntity media3 = new HotelMediaEntity(3L, "client-username1", "secret3", "client-first-name3", generateHotel());

        List<HotelMediaEntity> medias = new ArrayList();
        medias.add(media1);
        medias.add(media2);
        medias.add(media3);
        return medias;
    }
}
