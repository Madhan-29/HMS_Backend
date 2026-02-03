package com.springboot.HMS.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AllocateRoomRequestDTO {

    @NotBlank(message = "User email is required")
    private String userEmail;

    @NotNull(message = "Room ID is required")
    private Long roomId;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
