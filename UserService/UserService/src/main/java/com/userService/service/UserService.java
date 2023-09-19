package com.userService.service;

import com.userService.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    public User saveUserDetails(User user);

    public List<User> findAllDetails();
    public User findById(long id);

    public User updateDetailsOfUser(long id,User user);

    public String deleteParticularUser(long id);

}
