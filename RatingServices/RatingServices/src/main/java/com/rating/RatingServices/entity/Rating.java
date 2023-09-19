package com.rating.RatingServices.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ratingso")
public class Rating {
    private long userId;
    private long hotelId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ratingId;
    private String comments;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Hotel hotel;
}
