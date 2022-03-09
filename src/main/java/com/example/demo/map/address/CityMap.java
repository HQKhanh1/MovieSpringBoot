package com.example.demo.map.address;

import com.example.demo.DTO.address.CityDTO;
import com.example.demo.model.address.City;
import org.springframework.stereotype.Service;

@Service
public class CityMap {
    public CityDTO cityToDTO(City city){
        return new CityDTO(city.getId(), city.getName(), city.getGenre());
    }
}
