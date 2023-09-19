package com.userService.entity;

import javax.persistence.*;
import java.util.List;
@Entity
public class Rating {

    private long userId;
    private long hotelId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ratingId;
    private String comments;
    @ManyToMany(mappedBy = "ratings")
    private List<User> users;

}
