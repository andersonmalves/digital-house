package com.example.desafio_quality.interfaces;

import com.example.desafio_quality.dto.DistrictRequestDTO;
import com.example.desafio_quality.entity.District;

public interface IDistrictService {
    District createDistrict(DistrictRequestDTO district);
}
