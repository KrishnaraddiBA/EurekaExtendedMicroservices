package com.userService.service.impl;

import com.userService.entity.Hotel;
import com.userService.entity.Rating;
import com.userService.entity.User;
import com.userService.exception.ResurceNotFoundException;

//import com.userService.external.outsideMicroservice.HotelService;
//import com.userService.external.outsideMicroservice.RatingService;
import com.userService.external.outsideMicroservice.HotelService;
import com.userService.external.outsideMicroservice.RatingService;
import com.userService.repository.UserRepository;
import com.userService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;




    @Override
    public User saveUserDetails(User user) {
        User save = userRepository.save(user);


        return save;
    }

    @KafkaListener(topics = "Chandra",groupId = "group-1")
    public void receiveMsg(String s){
        logger.info("msg is {}",s);
    }

    @Override
    public List<User> findAllDetails() {

        List<User> all = userRepository.findAll();
        return all;
    }

    @Override
    public User findById(long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new ResurceNotFoundException("resourse of id" + id));
//now we use RestTemplate to get anather microservices url

//        Rating[] ratings = restTemplate.getForObject("http://localhost:8083/api/ratings/users/"+user.getId(), Rating[].class);
//        List<Rating> list = Arrays.stream(ratings).toList();
//        list = list.stream().map(rating -> {
////localhost:8082/api/hotels/findByIds/1
//            Hotel hotel = restTemplate.getForObject("http://localhost:8082/api/hotels/findByIds/" + rating.getHotelId(), Hotel.class);
//            rating.setHotel(hotel);
//
//            return rating;
//        }).collect(Collectors.toList());
//        user.setRatings(list);
        //this is about how to use feign client
                List<Rating> rates = ratingService.findAllRatings(user.getId());
        List<Hotel> collect = rates.stream().map(rating -> {
            Hotel byIds = hotelService.findByIds(rating.getHotelId());
            rating.setHotel(byIds);

            return byIds;
        }).collect(Collectors.toList());
        for (Rating r1:rates) {
            Hotel byIds = hotelService.findByIds(r1.getHotelId());
            r1.setHotel(byIds);

        }
        user.setRatings(rates);
        return user;
    }
    @Override
    public User updateDetailsOfUser(long id, User user) {

        User user1 = userRepository.findById(id).orElseThrow(() -> new ResurceNotFoundException("resorce of id" + id));
        user1.setName(user.getName());
        user1.setCity(user.getCity());
        user1.setCompany(user.getCompany());
        User save = userRepository.save(user1);
        return save;
    }

    @Override
    public String deleteParticularUser(long id) {

        User user1 = userRepository.findById(id).orElseThrow(() -> new ResurceNotFoundException("resorce of id" + id));


        userRepository.delete(user1);
        return "deleted sucessfully!!!";
    }
}

