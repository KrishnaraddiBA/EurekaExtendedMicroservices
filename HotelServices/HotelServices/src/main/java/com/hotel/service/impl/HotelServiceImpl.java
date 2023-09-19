package com.hotel.service.impl;

import com.hotel.entity.Hotel;
import com.hotel.exception.ResurceNotFoundException;
import com.hotel.repository.HotelRepository;
import com.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel saveHotels(Hotel hotel) {
        Hotel save = hotelRepository.save(hotel);
        return save;
    }

    @Override
    public List<Hotel> findAllHotels() {
        List<Hotel> all = hotelRepository.findAll();
        return all;
    }

    @Override
    public Hotel findByIds(long id) {

        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResurceNotFoundException("Not found" + id));
        return hotel;
    }

    @Override
    public String updateHotel(long id, Hotel hotel) {
        Hotel hotel1 = hotelRepository.findById(id).orElseThrow(() -> new ResurceNotFoundException("Not found" + id));
        hotel1.setCity(hotel.getCity());
        hotel1.setHotelName(hotel.getHotelName());
        hotelRepository.save(hotel1);


        return "updated sucessfully";
    }
}
