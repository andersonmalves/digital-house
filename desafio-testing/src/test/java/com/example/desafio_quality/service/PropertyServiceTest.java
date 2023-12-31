package com.example.desafio_quality.service;

import com.example.desafio_quality.dto.PropertyAreaDTO;
import com.example.desafio_quality.dto.PropertyRequestDTO;
import com.example.desafio_quality.dto.PropertyValueDTO;
import com.example.desafio_quality.dto.RoomRequestDTO;
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
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @InjectMocks
    private PropertyService service;

    @Mock
    private DistrictRepo repoDistrict;

    @Mock
    private PropertyRepo propertyRepo;
    private Property property;
    private District district;
    PropertyRequestDTO propertyRequest;
    RoomRequestDTO newRoom;

    private

    @BeforeEach
    void setup() {
        Room room1 = new Room(12.0, 12.0, "Quarto");
        Room room2 = new Room(24.0, 24.0, "Sala");
        List<Room> rooms = Arrays.asList(room1, room2);
        this.property = new Property("teste", 1, 1, rooms);
        property = new Property("teste", 1, 1, rooms);
        district = new District(1, new BigDecimal("24.000"), "Interlagos");
        this.newRoom = new RoomRequestDTO(25, 25, "Quarto Gamer");
        RoomRequestDTO roomRequestDTO = new RoomRequestDTO(12.0, 12.0, "Quarto");
        propertyRequest = new PropertyRequestDTO(property.getPropName(), property.getDistrictId(), List.of(roomRequestDTO));
    }

    @Test
    @DisplayName("Valida se retorna um PropertyDTO com a area correta")
    public void getArea_returnsArea_withCorrectPropertyId() {
        final double expectedArea = 720d;
        final int expectedRoomsQnt = 2;

        Mockito.when(propertyRepo.getPropertyById(anyInt()))
                .thenReturn(Optional.ofNullable(property));

        PropertyAreaDTO propertyArea = service.getArea(this.property.getPropId());

        assertThat(propertyArea).isNotNull();
        assertThat(propertyArea.getArea()).isEqualTo(expectedArea);
        assertThat(propertyArea.getRoomsQnt()).isEqualTo(expectedRoomsQnt);
    }

    @Test
    @DisplayName("Valida se retorna um erro com o id incorreto")
    public void getArea_returnsExceptionNotFound_withIncorrectPropertyId() {
        final int invalidPropertyId = 999;

        Mockito.when(propertyRepo.getPropertyById(anyInt()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.getArea(invalidPropertyId);
        });
    }

    @Test
    @DisplayName("Valida se retorna retorna a Room de maior área")
    public void getBiggestRoom_returnsRoom_withBiggestArea() {
        final double expectedBiggestRoomArea = 576d;

        Mockito.when(propertyRepo.getPropertyById(anyInt()))
                .thenReturn(Optional.ofNullable(this.property));

        Room response = this.service.getBiggestRoom(this.property.getPropId());
        double responseArea = response.getRoomWidth() * response.getRoomLength();

        assertThat(response).isNotNull();
        assertThat(response.getRoomName()).isNotNull();
        assertThat(responseArea).isEqualTo(expectedBiggestRoomArea);
    }

    @Test
    @DisplayName("Valida se retorna um erro com o id incorreto")
    public void getBiggestRoom_returnsExceptionNotFound_withIncorrectPropertyId(){
        final int invalidPropertyId = 999;

        Mockito.when(propertyRepo.getPropertyById(anyInt()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.getBiggestRoom(invalidPropertyId);
        });
    }

    @Test
    @DisplayName("Valida se retorna um erro quando a propriedade não possui cômodos")
    public void getBiggestRoom_returnsExceptionNotFound_withNoRooms(){
        final Property propertyWithNoRooms = new Property("teste",
                1, 1, null);

        Mockito.when(propertyRepo.getPropertyById(anyInt()))
                .thenReturn(Optional.of(propertyWithNoRooms));

        assertThrows(NotFoundException.class, () -> {
            service.getBiggestRoom(propertyWithNoRooms.getPropId());
        });
    }

    @Test
    @DisplayName("Valida se retora todos os cômodos de uma propriedade")
    public void getAllRooms_returnAllRooms_withPropertyHaveRooms() {
        Mockito.when(propertyRepo.getPropertyById(anyInt()))
                .thenReturn(Optional.ofNullable(property));

        List<Room> propertyWithRooms = service.getRooms(this.property.getPropId());

        assertThat(propertyWithRooms).isNotNull();
        assertThat(propertyWithRooms).isEqualTo(property.getRooms());
    }

    @Test
    @DisplayName("Valida se retorna um erro com o id incorreto")
    public void getAllRoom_returnsExceptionNotFound_withIncorrectPropertyId() {
        final int invalidPropertyId = 999;

        Mockito.when(propertyRepo.getPropertyById(anyInt()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.getRooms(invalidPropertyId);
        });
    }

    @Test
    @DisplayName("Verifica se retorna o valor total do imóvel")
    void getValue_returnCorrectValue() {
        Mockito.when(propertyRepo.getPropertyById(anyInt()))
                .thenReturn(Optional.ofNullable(property));
        Mockito.when(repoDistrict.getDistrictById(anyInt()))
                .thenReturn(Optional.ofNullable(district));

        PropertyValueDTO propertyValue = service.getValue(1);

        assertThat(propertyValue).isNotNull();
        assertThat(propertyValue.getValue()).isEqualTo(new BigDecimal("17280.0000"));
    }

    @Test
    @DisplayName("Valida se retorna um erro caso o id da propriedade seja inválido")
    void getValue_returnsNotFoundException_withIncorrectPropertyId() {
        Mockito.when(propertyRepo.getPropertyById(anyInt()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> {
            service.getValue(1);
        });
    }

    @Test
    @DisplayName("Valida se retorna um erro caso o id do bairro seja inválido")
    void getValue_returnsNotFoundException_withIncorrectDistrictId() {
        Mockito.when(propertyRepo.getPropertyById(anyInt()))
                .thenReturn(Optional.ofNullable(property));
        Mockito.when(repoDistrict.getDistrictById(anyInt()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> {
            service.getValue(1);
            });
    }

    @Test
    @DisplayName("Valida se retorna uma Property com os parâmetros corretos")
    void createProperty_returnsNewProperty_withCorrectParams() {
        Mockito.when(repoDistrict.getDistrictById(anyInt()))
                .thenReturn(Optional.ofNullable(district));

        Mockito.when(propertyRepo.createProperty(ArgumentMatchers.any()))
                .thenReturn(property);

        Property newProperty = service.createProperty(propertyRequest);

        assertThat(newProperty).isNotNull();
        assertThat(newProperty.getDistrictId()).isEqualTo(property.getDistrictId());
        assertThat(newProperty.getPropId()).isEqualTo(property.getPropId());
        assertThat(newProperty.getPropName()).isEqualTo(property.getPropName());
        assertThat(newProperty.getRooms()).isEqualTo(property.getRooms());
    }

    @Test
    @DisplayName("Valida se retorna um erro caso o id do bairro seja inválido naa criação da propriedade")
    void createProperty_returnsNotFoundException_withIncorrectDistrictId() {
        Mockito.when(repoDistrict.getDistrictById(anyInt()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.createProperty(propertyRequest);
        });
    }

    @Test
    @DisplayName("Valida a criação de um novo cômodo com os parâmetros corretos")
    void createRooms_returnRoomCreated_whenHaveCorrectPropriety() {
        RoomRequestDTO roomRequest = new RoomRequestDTO(25, 25, "Cinema");
        Room roomCreated = new Room(roomRequest.getRoomWidth(), roomRequest.getRoomLength(), roomRequest.getRoomName());

        Mockito.when(propertyRepo.createRooms(ArgumentMatchers.any(), ArgumentMatchers.anyInt()))
                .thenReturn(roomCreated);

        Mockito.when(propertyRepo.getPropertyById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.ofNullable(property));

        Room room = service.createRooms(newRoom, property.getPropId());


        assertThat(room).isNotNull();
        assertThat(room.getRoomLength()).isEqualTo(roomCreated.getRoomLength());
        assertThat(room.getRoomWidth()).isEqualTo(roomCreated.getRoomWidth());
        assertThat(room.getRoomName()).isEqualTo(roomCreated.getRoomName());
    }
}
