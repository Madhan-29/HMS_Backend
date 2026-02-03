package com.springboot.HMS.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class RoomRequestDTO {

    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @Min(value = 1, message = "Capacity must be at least 1")
    private int capacity;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
