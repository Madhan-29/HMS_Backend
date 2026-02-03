package com.springboot.HMS.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.springboot.HMS.dto.AttendanceRequestDTO;
import com.springboot.HMS.dto.AttendanceResponseDTO;

public interface AttendanceService {

    ResponseEntity<?> markAttendance(AttendanceRequestDTO req, String email);

    List<AttendanceResponseDTO> myAttendance(String email);

    List<AttendanceResponseDTO> allAttendance(String date);
}
