package com.userService.service.impl;

import com.userService.entity.Hotel;
import com.userService.entity.Rating;
import com.userService.entity.User;
import com.userService.exception.ResurceNotFoundException;
import com.userService.repository.UserRepository;
import com.userService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUserDetails(User user) {
        User save = userRepository.save(user);


        return save;
    }

    @Override
    public List<User> findAllDetails() {

        List<User> all = userRepository.findAll();
        return all;
    }

    @Override
    public User findById(long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new ResurceNotFoundException("resourse of id" + id));
        //http://localhost:8083/api/ratings/users/1
        Rating[] r = restTemplate.getForObject("http://localhost:8083/api/ratings/users/" + user.getId(), Rating[].class);

        List<Rating> list = Arrays.stream(r).toList();
        logger.info("{}" + r);
        user.setRatings(list);
        List<Rating> collect = list.stream().map(s -> {
            System.out.println(s.getHotelId());

            Hotel hotel = restTemplate.getForObject("http://localhost:8082/api/hotels/findByIds/" + s.getHotelId(), Hotel.class);

            s.setHotel(hotel);
            return s;

        }).collect(Collectors.toList());
        return user;
    }

//        //http://localhost:8082/api/hotels/findByIds/1
//        Hotel hotel = restTemplate.getForObject("http://localhost:8082/api/hotels/findByIds/"+, Hotel.class);
//    logger.info(" {}",hotel);
//
//
//        return user;
//    }

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
