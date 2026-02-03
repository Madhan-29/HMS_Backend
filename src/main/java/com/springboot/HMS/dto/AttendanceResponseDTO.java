package com.springboot.HMS.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceResponseDTO {

    private Long id;
    private String userEmail;
    private LocalDate date;
    private boolean present;
    private LocalDateTime markedAt;

    public AttendanceResponseDTO(Long id, String userEmail, LocalDate date, boolean present, LocalDateTime markedAt) {
        this.id = id;
        this.userEmail = userEmail;
        this.date = date;
        this.present = present;
        this.markedAt = markedAt;
    }

    public Long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isPresent() {
        return present;
    }

    public LocalDateTime getMarkedAt() {
        return markedAt;
    }
}
