package com.hotel.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "hotels")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotelId")
    private long id;
    @Column(name="hotelName")
    @NotNull
    private String hotelName;
    @Column(name = "city")
    @NotNull
    private String city;

}
