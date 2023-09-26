package com.userService.external.outsideMicroservice;

import com.userService.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("api/hotels/findByIds/{id}")
    public Hotel findByIds(@PathVariable long id);
}
