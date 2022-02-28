package com.example.demo.map.address;

import com.example.demo.DTO.address.TownDTO;
import com.example.demo.model.address.Town;
import org.springframework.stereotype.Service;

@Service
public class TownMap {
    public TownDTO townToDTO(Town town) {
        if (town == null) {
            return null;
        } else {
            return new TownDTO(town.getId(), town.getName(), town.getGenre());
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
