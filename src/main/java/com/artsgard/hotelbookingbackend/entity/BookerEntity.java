package com.artsgard.hotelbookingbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "booker")
public class BookerEntity implements Serializable { // UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_in_date", nullable = false)
    private Date checkInDate;

    @NotNull
    @Column(name = "check_out_date", nullable = false)
    private Date checkOutDate;

    @NotNull
    @Column(name = "rooms", nullable = true)
    private Integer rooms;
    
    public enum RoomType {
        SINGLE, DOUBLE, TRIPLE
    }
    
    @NotNull
    @Column(name = "room_type", length = 100)
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    
    @NotNull
    @Column(name = "breakfast_included", nullable = false)
    private Boolean breakfastIncluded;

    @NotNull
    @Column(name = "booking_date", nullable = false)
    private Date bookingDate;
    
    @NotNull
    @Column(name = "nights", nullable = false)
    private Integer nights;
    
    @NotNull
    @Column(name = "final_price", nullable = false)
    private BigDecimal finalPrice;

    @NotNull
    @JsonIgnoreProperties("clientBookings")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;
    
    @NotNull
    @JsonIgnoreProperties("hotelBookings")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;
}
