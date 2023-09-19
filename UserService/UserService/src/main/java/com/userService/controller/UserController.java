package com.userService.controller;

import com.userService.entity.User;
import com.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
private UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<User> saveAllUsers(@RequestBody User user){
        User user1 = userService.saveUserDetails(user);
    return new ResponseEntity<>(user1, HttpStatus.OK);
    }
    @GetMapping("/findUser/{id}")
    public ResponseEntity<User> findByIdDetails(@PathVariable("id") long id){
        User byId = userService.findById(id);

    return new ResponseEntity<>(byId,HttpStatus.FOUND);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> findAllDetails(){
        List<User> allDetails = userService.findAllDetails();
    return new ResponseEntity<>(allDetails,HttpStatus.FOUND);
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id,@RequestBody User user){
        User user1 = userService.updateDetailsOfUser(id, user);

   return new ResponseEntity<>(user1,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        String s = userService.deleteParticularUser(id);
return new ResponseEntity<>(s,HttpStatus.OK);
    }


}
