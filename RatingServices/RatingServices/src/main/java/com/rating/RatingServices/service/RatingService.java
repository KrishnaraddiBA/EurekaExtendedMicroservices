package com.rating.RatingServices.service;

import com.rating.RatingServices.entity.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {


    public Rating saveRatings(Rating rating);

    public List<Rating> findAllRatings();
    public List<Rating> findRatingByUser(long id);

    public Rating findByIds(long id);
}
