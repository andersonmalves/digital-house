package com.example.desafio_quality.dto;


import com.example.desafio_quality.entity.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomDTO {
    private double roomWidth, roomLength;
    private String roomName;
    private double area;

    /**
     * Conversor da classe room para dto
     * @author Gabriel, Felipe
     * @param room classe Room da entity
     */
    public RoomDTO(Room room) {
        this.roomWidth = room.getRoomWidth();
        this.roomLength = room.getRoomLength();
        this.roomName = room.getRoomName();
        this.area = (room.getRoomWidth() * room.getRoomLength());
    }
}
