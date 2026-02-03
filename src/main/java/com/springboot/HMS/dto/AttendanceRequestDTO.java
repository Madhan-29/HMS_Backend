package com.springboot.HMS.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class AttendanceRequestDTO {

    @NotNull(message = "Date is required")
    private LocalDate date;

    private boolean present = true; 

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}
