package com.example.desafio_quality.service;

import com.example.desafio_quality.dto.DistrictRequestDTO;
import com.example.desafio_quality.entity.District;
import com.example.desafio_quality.repository.DistrictRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DistrictServiceTest {

    @InjectMocks
    private DistrictService districtService;

    @Mock
    private DistrictRepo districtRepo;

    @Test
    @DisplayName("Valida se retorna um District com os parâmetros corretos")
    void createDistrict_returnsNewDistrict_withCorrectParams() {
        DistrictRequestDTO districtRequest = new DistrictRequestDTO("Interlagos", new BigDecimal("24.000"));
        final int DISTRICT_ID = 1;
        District newDistrict = new District(DISTRICT_ID, districtRequest.getValueDistrictM2(), districtRequest.getDistrictName());

        Mockito.when(districtRepo.createDistrict(ArgumentMatchers.any()))
                .thenReturn(newDistrict);

        District district = districtService.createDistrict(districtRequest);

        assertThat(district).isNotNull();
        assertThat(district.getDistrictId()).isEqualTo(newDistrict.getDistrictId());
        assertThat(district.getDistrictName()).isEqualTo(newDistrict.getDistrictName());
        assertThat(district.getValueDistrictM2()).isEqualTo(newDistrict.getValueDistrictM2());
    }
}
