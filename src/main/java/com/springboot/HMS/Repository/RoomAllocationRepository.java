package com.springboot.HMS.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.HMS.Entity.RoomAllocation;
import com.springboot.HMS.Entity.UserEntity;

@Repository
public interface RoomAllocationRepository extends JpaRepository<RoomAllocation, Long> {

    Optional<RoomAllocation> findByUserAndActiveTrue(UserEntity user);

    List<RoomAllocation> findByActiveTrue();
}
