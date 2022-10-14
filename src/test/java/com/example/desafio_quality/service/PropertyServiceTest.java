package com.example.desafio_quality.service;

import com.example.desafio_quality.dto.PropertyAreaDTO;
import com.example.desafio_quality.dto.PropertyValueDTO;
import com.example.desafio_quality.entity.District;
import com.example.desafio_quality.entity.Property;
import com.example.desafio_quality.entity.Room;
import com.example.desafio_quality.exception.NotFoundException;
import com.example.desafio_quality.repository.DistrictRepo;
import com.example.desafio_quality.repository.PropertyRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @InjectMocks
    private PropertyService service;

    @Mock
    private PropertyRepo repo;
    @Mock
    private DistrictRepo repoDistrict;

    private Property property;
    private District district;

    @BeforeEach
    void setup() {
        Room room1 = new Room(12, 12, "Quarto");
        Room room2 = new Room(24.0, 24, "Sala");
        List<Room> rooms = Arrays.asList(room1, room2);
        property = new Property("teste", 1, 1, rooms);
        district = new District(1, new BigDecimal("24.000"), "Interlagos");
    }

    @Test
    @DisplayName("Valida se retorna um PropertyDTO com a area correta")
    void getArea_returnsArea_withCorrectPropertyId() {
        Mockito.when(repo.getPropertyById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.ofNullable(property));

        PropertyAreaDTO propertyArea = service.getArea(1);

        assertThat(propertyArea).isNotNull();
        assertThat(propertyArea.getArea()).isEqualTo(720.0);
        assertThat(propertyArea.getRoomsQnt()).isEqualTo(2);
    }

    @Test
    @DisplayName("Valida se retorna um erro com o id incorreto")
    void getArea_returnsExceptionNotFound_withIncorrectPropertyId() {
        Mockito.when(repo.getPropertyById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.getArea(2);
        });
    }

    @Test
    @DisplayName("Verifica se retorna o valor total do imóvel")
    void getValue_returnCorrectValue() {
        Mockito.when(repo.getPropertyById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.ofNullable(property));
        Mockito.when(repoDistrict.getDistrictById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.ofNullable(district));

        PropertyValueDTO propertyValue = service.getValue(1);

        assertThat(propertyValue).isNotNull();
        assertThat(propertyValue.getValue()).isEqualTo(new BigDecimal("17280.0000"));
    }

    @Test
    @DisplayName("Valida se retorna um erro caso o id da propriedade seja inválido")
    void getValue_returnsNotFoundException_withIncorrectPropertyId() {
        Mockito.when(repo.getPropertyById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> {
            service.getValue(1);
        });
    }

    @Test
    @DisplayName("Valida se retorna um erro caso o id do bairro seja inválido")
    void getValue_returnsNotFoundException_withIncorrectDistrictId() {
        Mockito.when(repo.getPropertyById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.ofNullable(property));
        Mockito.when(repoDistrict.getDistrictById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> {
            service.getValue(1);
        });
    }
}
