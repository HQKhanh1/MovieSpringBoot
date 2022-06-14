package com.example.demo.service.addressService;


import com.example.demo.DTO.address.DistrictDTO;
import com.example.demo.DTO.address.TownDTO;

import java.util.List;

public interface DistrictService {

    List<TownDTO> getTownByDistrictId(int id);

    DistrictDTO getById(int id);
}
