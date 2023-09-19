package com.rating.RatingServices.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hotel {

    @Id
    @Column(name = "hotelId")
    private long id;
    private String hotelName;
    private String city;
@OneToOne(mappedBy = "hotel")
private Rating rating;
}
