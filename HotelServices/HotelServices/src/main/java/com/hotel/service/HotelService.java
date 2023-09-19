package com.hotel.service;

import com.hotel.entity.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface HotelService {

    public Hotel saveHotels(Hotel hotel);
    public List<Hotel> findAllHotels();
    public Hotel findByIds(long id);
    public String updateHotel(long id,Hotel hotel);
}
