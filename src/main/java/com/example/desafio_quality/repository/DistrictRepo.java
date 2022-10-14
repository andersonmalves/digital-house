package com.example.desafio_quality.repository;

import com.example.desafio_quality.entity.District;
import com.example.desafio_quality.util.ManipulateFile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DistrictRepo {
    public Optional<District> getDistrictById(int id) {
        List<District> districts = ManipulateFile.loadDistricts();

        return districts.stream()
                .filter(p -> p.getDistrictId() == id)
                .findFirst();
    }
}
