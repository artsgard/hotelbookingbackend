package com.artsgard.hotelbookingbackend.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotel")
public class HotelEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name", length = 100)
    private String name;
    
    @Column(name = "description", length = 1000)
    private String description;
    
    @NotNull
    @Column(name = "street", length = 100)
    private String street;
    
    @NotNull
    @Column(name = "city", length = 1000)
    private String city;
    
    @NotNull
    @Column(name = "single_room", length = 1000)
    private BigDecimal singleRoom;
    
    @NotNull
    @Column(name = "double_room", length = 1000)
    private BigDecimal doubleRoom;
    

    @Column(name = "triple_room", length = 1000)
    private BigDecimal tripleRoom;
    
    @NotNull
    @Column(name = "breakfast_included", length = 1000)
    private Boolean breakfastIncluded;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_media_id")
    private HotelMediaEntity hotelMedia;
    
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE)
    private List<BookerEntity> hotelBookings;
}
