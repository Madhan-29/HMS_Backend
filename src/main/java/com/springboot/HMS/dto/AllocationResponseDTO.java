package com.springboot.HMS.dto;

import java.time.LocalDateTime;

public class AllocationResponseDTO {

    private Long allocationId;
    private String userEmail;
    private String roomNumber;
    private int capacity;
    private int occupied;
    private boolean active;
    private LocalDateTime allocatedAt;

    public AllocationResponseDTO(Long allocationId, String userEmail, String roomNumber,int capacity, int occupied, boolean active, LocalDateTime allocatedAt) {
        this.allocationId = allocationId;
        this.userEmail = userEmail;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.occupied = occupied;
        this.active = active;
        this.allocatedAt = allocatedAt;
    }

    public Long getAllocationId() {
        return allocationId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupied() {
        return occupied;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getAllocatedAt() {
        return allocatedAt;
    }
}
