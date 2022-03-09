package com.example.demo.map.address;

import com.example.demo.DTO.address.TownDTO;
import com.example.demo.model.address.Town;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TownMap {
    private final DistrictMap districtMap;
    public TownDTO townToDTO(Town town) {
        if (town == null) {
            return null;
        } else {
            return new TownDTO(town.getId(), town.getName(), town.getGenre(), districtMap.districtToDTO(town.getDistrict()));
        }
    }

    public Town DTOToTown(TownDTO townDTO) {
        Town town = new Town();
        town.setId(townDTO.getId());
        town.setGenre(townDTO.getGenre());
        town.setName(townDTO.getName());
        return town;
    }
}
