package com.example.demo.map.address;

import com.example.demo.DTO.address.DistrictDTO;
import com.example.demo.model.address.District;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DistrictMap {
    private final CityMap cityMap;
    public DistrictDTO districtToDTO(District district) {
        if (district == null) {
            return null;
        } else {
            return new DistrictDTO(district.getId(), district.getName(), district.getGenre(), cityMap.cityToDTO(district.getCity()));
        }
    }
}
