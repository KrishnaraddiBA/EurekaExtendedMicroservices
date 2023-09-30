package com.rating.RatingServices.service.impl;

import com.rating.RatingServices.entity.Rating;
import com.rating.RatingServices.repository.RatingRepository;
import com.rating.RatingServices.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Override
    public Rating saveRatings(Rating rating) {
        Rating save = ratingRepository.save(rating);


        return save;
    }

    @Override
    public List<Rating> findAllRatings() {

        List<Rating> all = ratingRepository.findAll();
        return all;
    }

    @Override
    public List<Rating> findRatingByUser(long id) {

        Optional<List<Rating>> byIdsByUser = ratingRepository.findByUserId(id);
        if (byIdsByUser.isEmpty()) {
            throw new RuntimeException("UserNotMadeAnyRatingsOrDoesNotExists");
        } else {
            List<Rating> ratings = byIdsByUser.get();
            String s = String.valueOf(ratings);
            kafkaTemplate.send("Chandra", s);
            return ratings;
        }
    }

    @Override
    public Rating findByIds(long id) {
        Rating idDoesNotExists = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("id does not exists"));

        return idDoesNotExists;
    }
}
