package com.userService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @Column(name = "hotelId")
    private long id;
    private String hotelName;
    private String city;
    @OneToOne(mappedBy = "hotel")
    private Rating rating;
}
