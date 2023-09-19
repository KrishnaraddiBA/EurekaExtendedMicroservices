package com.userService.service.impl;

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
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
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
        List<Rating> r= restTemplate.getForObject("http://localhost:8083/api/ratings/users/1", ArrayList.class);

        logger.info("{}"+r);
        user.setRatings(r);


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
