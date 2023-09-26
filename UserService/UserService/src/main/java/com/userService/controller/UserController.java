package com.userService.controller;

import com.userService.entity.User;
import com.userService.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
private UserService userService;

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    //localhost::8081/api/user/saveUser
    @PostMapping("/saveUser")
    public ResponseEntity<User> saveAllUsers(@RequestBody User user){
        User user1 = userService.saveUserDetails(user);
    return new ResponseEntity<>(user1, HttpStatus.OK);
    }
    //localhost:8081/api/user/findUser/{id}



//    int retryCount=1;
    @GetMapping("/findUser/{id}")
    @CircuitBreaker(name = "Chandrayana",fallbackMethod = "ratingHotelTolerence")
//    @Retry(name="Chandrayana",fallbackMethod = "ratingHotelTolerence")
//    @RateLimiter(name="Chandrayana",fallbackMethod = "ratingHotelTolerence")
    public ResponseEntity<User> findByIdDetails(@PathVariable("id") long id){
//        logger.info("the retry no {}",retryCount);
//        retryCount++;
        User byId = userService.findById(id);

    return new ResponseEntity<>(byId,HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<User> ratingHotelTolerence(long id,Exception ex){

        logger.info("there is an internal crash in the server of {}",ex.getMessage());
        User user=User.builder().email(
                "dummy@gmail.com").company("unknown_crash").city(
                        "unkmown").name("unknown").id(id).build();

        return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
    }


//localhost:8081/api/user/getAllUser
    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> findAllDetails(){
        List<User> allDetails = userService.findAllDetails();
    return new ResponseEntity<>(allDetails,HttpStatus.FOUND);
    }
    //localhost:8081/api/user/updateUser/{id}
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id,@RequestBody User user){
        User user1 = userService.updateDetailsOfUser(id, user);

   return new ResponseEntity<>(user1,HttpStatus.ACCEPTED);
    }
    //localhost:8081/api/user/deleteUser/{id}
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        String s = userService.deleteParticularUser(id);
return new ResponseEntity<>(s,HttpStatus.OK);
    }


}
