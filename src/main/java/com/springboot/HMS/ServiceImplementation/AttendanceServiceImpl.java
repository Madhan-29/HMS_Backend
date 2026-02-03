package com.springboot.HMS.ServiceImplementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.HMS.Entity.Attendance;
import com.springboot.HMS.Entity.UserEntity;
import com.springboot.HMS.Repository.AttendanceRepository;
import com.springboot.HMS.Repository.UserRepository;
import com.springboot.HMS.Service.AttendanceService;
import com.springboot.HMS.dto.AttendanceRequestDTO;
import com.springboot.HMS.dto.AttendanceResponseDTO;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> markAttendance(AttendanceRequestDTO req, String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate date = req.getDate();

        if (attendanceRepository.findByUserAndDate(user, date).isPresent()) {
            return ResponseEntity.badRequest().body("Attendance already marked for this date");
        }

        Attendance att = new Attendance();
        att.setUser(user);
        att.setDate(date);
        att.setPresent(req.isPresent());
        att.setMarkedAt(LocalDateTime.now());

        attendanceRepository.save(att);
        return ResponseEntity.ok("Attendance marked successfully");
    }

    @Override
    public List<AttendanceResponseDTO> myAttendance(String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return attendanceRepository.findByUser(user)
                .stream()
                .map(a -> new AttendanceResponseDTO(
                        a.getId(),
                        a.getUser().getEmail(),
                        a.getDate(),
                        a.isPresent(),
                        a.getMarkedAt()
                ))
                .toList();
    }

    @Override
    public List<AttendanceResponseDTO> allAttendance(String date) {

        List<Attendance> list;

        if (date == null) {
            list = attendanceRepository.findAll();
        } else {
            list = attendanceRepository.findByDate(LocalDate.parse(date));
        }

        return list.stream()
                .map(a -> new AttendanceResponseDTO(
                        a.getId(),
                        a.getUser().getEmail(),
                        a.getDate(),
                        a.isPresent(),
                        a.getMarkedAt()
                ))
                .toList();
    }
}
