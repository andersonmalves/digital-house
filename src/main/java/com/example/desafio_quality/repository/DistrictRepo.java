package com.example.desafio_quality.repository;

import com.example.desafio_quality.entity.District;
import com.example.desafio_quality.util.ManipulateFile;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DistrictRepo {

    /**
     * Realiza a busca do bairro pelo ID.
     * @author Anderson, Giovanna
     * @param id ID do bairro.
     * @return Retorna o bairro por ID caso haja correspondência.
     */
    public Optional<District> getDistrictById(int id) {
        List<District> districts = ManipulateFile.loadDistricts();

        return districts.stream()
                .filter(p -> p.getDistrictId() == id)
                .findFirst();
    }

    /**
     * Salva o novo distrito na base de dados
     * @author Gabriel
     * @param newDistrict o novo distrito a ser salvo
     * @return Retorna o novo distrito salvo na base de dados
     */
    public District createDistrict(District newDistrict) {
        List<District> districts = ManipulateFile.loadDistricts();
        districts.add(newDistrict);
        ManipulateFile.saveDistricts(districts);
        return newDistrict;
    }

    public void deleteAllDistricts() {
        List<District> districts = new ArrayList<District>();
        ManipulateFile.saveDistricts(districts);
    }
}
