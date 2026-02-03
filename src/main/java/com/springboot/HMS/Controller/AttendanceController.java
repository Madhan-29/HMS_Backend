package com.springboot.HMS.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.springboot.HMS.Service.AttendanceService;
import com.springboot.HMS.dto.AttendanceRequestDTO;
import com.springboot.HMS.dto.AttendanceResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/attendance")
@Validated
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/mark")
    public ResponseEntity<?> markAttendance(@RequestBody @Valid AttendanceRequestDTO req, Authentication auth) {
        return attendanceService.markAttendance(req, auth.getName());
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public List<AttendanceResponseDTO> myAttendance(Authentication auth) {
        return attendanceService.myAttendance(auth.getName());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<AttendanceResponseDTO> allAttendance(@RequestParam(required = false) String date) {
        return attendanceService.allAttendance(date);
    }
}
