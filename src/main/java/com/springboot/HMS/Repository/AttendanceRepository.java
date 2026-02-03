package com.springboot.HMS.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.HMS.Entity.Attendance;
import com.springboot.HMS.Entity.UserEntity;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByUserAndDate(UserEntity user, LocalDate date);

    List<Attendance> findByUser(UserEntity user);

    List<Attendance> findByDate(LocalDate date);
}
