package com.userService.external.outsideMicroservice;

import com.userService.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

    @GetMapping("api/ratings/users/{id}")
    public List<Rating> findAllRatings(@PathVariable("id") long userId);
}
