package com.example.desafio_quality.service;

import com.example.desafio_quality.dto.DistrictRequestDTO;
import com.example.desafio_quality.entity.District;
import com.example.desafio_quality.interfaces.IDistrictService;
import com.example.desafio_quality.repository.DistrictRepo;
import com.example.desafio_quality.util.GenerateDistrictId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictService implements IDistrictService {

    @Autowired
    private DistrictRepo districtRepo;

    /**
     * Cria um distrito a partir de um request
     * @author Gabriel
     * @param district um novo distrito a ser criado
     * @return retorna um novo distrito criado na base de dados
     */
    @Override
    public District createDistrict(DistrictRequestDTO district) {
        int newDistrictId = GenerateDistrictId.getIdGenerator().getNext();
        District newDistrict = this.districtRepo.createDistrict(new District(newDistrictId,
                district.getValueDistrictM2(), district.getDistrictName()));

        return newDistrict;
    }
}
