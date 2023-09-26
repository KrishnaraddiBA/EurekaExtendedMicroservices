package com.hotel.controller;

import com.hotel.entity.Hotel;
import com.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    //localhost:8082/api/hotels/postData

    @PreAuthorize("hasAuthority('Admin')")
   @PostMapping("/postData")
    public ResponseEntity<Hotel> saveHotelInformation(@RequestBody Hotel hotel){
        Hotel hotel1 = hotelService.saveHotels(hotel);
    return new ResponseEntity<>(hotel1, HttpStatus.OK);
    }
    //localhost:8082/api/hotels/getAll
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/getAll")
    public ResponseEntity<List<Hotel>> findAllHotels(){
        List<Hotel> allHotels = hotelService.findAllHotels();
return new ResponseEntity<>(allHotels,HttpStatus.OK);
    }
    //localhost:8082/api/hotels/findByIds/{id}

    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/findByIds/{id}")
    public ResponseEntity<Hotel> findByIds(@PathVariable("id") long id){
        Hotel byIds = hotelService.findByIds(id);
    return new ResponseEntity<>(byIds,HttpStatus.OK);
    }
    //localhost:8082/api/hotels/updateHotels/{id}
    @PutMapping("/updateHotels/{id}")
    public ResponseEntity<String> updateHotels(@PathVariable("id") long id,@RequestBody Hotel hotel){
        String s = hotelService.updateHotel(id, hotel);
    return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
    }

}
