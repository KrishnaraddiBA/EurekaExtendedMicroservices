package com.userService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private long userId;
    private long hotelId;
    @Id

    private long ratingId;
    private String comments;
    @ManyToMany(mappedBy = "ratings")
    private List<User> users;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Hotel hotel;

}
