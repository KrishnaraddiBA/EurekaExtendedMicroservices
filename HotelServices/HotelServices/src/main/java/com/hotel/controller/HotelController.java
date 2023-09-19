package com.hotel.controller;

import com.hotel.entity.Hotel;
import com.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;
   @PostMapping("/postData")
    public ResponseEntity<Hotel> saveHotelInformation(@RequestBody Hotel hotel){
        Hotel hotel1 = hotelService.saveHotels(hotel);
    return new ResponseEntity<>(hotel1, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Hotel>> findAllHotels(){
        List<Hotel> allHotels = hotelService.findAllHotels();
return new ResponseEntity<>(allHotels,HttpStatus.OK);
    }
    @GetMapping("/findByIds/{id}")
    public ResponseEntity<Hotel> findByIds(@PathVariable("id") long id){
        Hotel byIds = hotelService.findByIds(id);
    return new ResponseEntity<>(byIds,HttpStatus.OK);
    }
    @PutMapping("/updateHotels/{id}")
    public ResponseEntity<String> updateHotels(@PathVariable("id") long id,@RequestBody Hotel hotel){
        String s = hotelService.updateHotel(id, hotel);
    return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
    }

}
