package com.rating.RatingServices.controller;

import com.rating.RatingServices.entity.Rating;
import com.rating.RatingServices.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    //localhost:8083/api/ratings/post

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/post")
    public ResponseEntity<Rating> saveRatings(@RequestBody Rating rating){
        Rating rating1 = ratingService.saveRatings(rating);
    return new ResponseEntity<>(rating1, HttpStatus.OK);
    }
    //localhost:8083/api/ratings/users/{userId}

//    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> findAllRatings(@PathVariable("userId") long id){
        List<Rating> ratingByUser = ratingService.findRatingByUser(id);
return new ResponseEntity<>(ratingByUser,HttpStatus.OK);
    }
    //localhost:8083/api/ratings/ratings/{ratingId}

    @GetMapping("/ratings/{ratingId}")
    public ResponseEntity<Rating> findRatingsById(@PathVariable("ratingId") long id){
        Rating byIds = ratingService.findByIds(id);

    return new ResponseEntity<>(byIds,HttpStatus.FOUND);
    }
    //localhost:8083/api/ratings/findAll
    @GetMapping("/findAll")
    public ResponseEntity<List<Rating>> findAllRatings(){
        List<Rating> allRatings = ratingService.findAllRatings();
return new ResponseEntity<>(allRatings,HttpStatus.FOUND);
    }

}
