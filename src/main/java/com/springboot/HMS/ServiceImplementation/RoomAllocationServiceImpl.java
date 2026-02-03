package com.springboot.HMS.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.HMS.Entity.Room;
import com.springboot.HMS.Entity.RoomAllocation;
import com.springboot.HMS.Entity.UserEntity;
import com.springboot.HMS.Repository.RoomAllocationRepository;
import com.springboot.HMS.Repository.RoomRepository;
import com.springboot.HMS.Repository.UserRepository;
import com.springboot.HMS.Service.RoomAllocationService;
import com.springboot.HMS.dto.AllocateRoomRequestDTO;
import com.springboot.HMS.dto.AllocationResponseDTO;

@Service
public class RoomAllocationServiceImpl implements RoomAllocationService {

    private final RoomAllocationRepository allocationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public RoomAllocationServiceImpl(RoomAllocationRepository allocationRepository,
                                     RoomRepository roomRepository,
                                     UserRepository userRepository) {
        this.allocationRepository = allocationRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> assignRoom(AllocateRoomRequestDTO req) {

        UserEntity user = userRepository.findByEmail(req.getUserEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Room room = roomRepository.findById(req.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (allocationRepository.findByUserAndActiveTrue(user).isPresent()) {
            return ResponseEntity.badRequest().body("User already has a room allocated");
        }

        if (room.getOccupied() >= room.getCapacity()) {
            room.setAvailable(false);
            roomRepository.save(room);
            return ResponseEntity.badRequest().body("Room is full");
        }

        RoomAllocation allocation = new RoomAllocation();
        allocation.setUser(user);
        allocation.setRoom(room);
        allocation.setAllocatedAt(LocalDateTime.now());
        allocation.setActive(true);

        allocationRepository.save(allocation);

        room.setOccupied(room.getOccupied() + 1);
        room.setAvailable(room.getOccupied() < room.getCapacity());
        roomRepository.save(room);

        return ResponseEntity.ok("Room allocated successfully");
    }

    @Override
    public List<AllocationResponseDTO> getAllActiveAllocations() {
        return allocationRepository.findByActiveTrue()
                .stream()
                .map(a -> new AllocationResponseDTO(
                        a.getId(),
                        a.getUser().getEmail(),
                        a.getRoom().getRoomNumber(),
                        a.getRoom().getCapacity(),
                        a.getRoom().getOccupied(),
                        a.isActive(),
                        a.getAllocatedAt()
                ))
                .toList();
    }

    @Override
    public ResponseEntity<String> vacateRoom(Long allocationId) {

        RoomAllocation allocation = allocationRepository.findById(allocationId)
                .orElseThrow(() -> new RuntimeException("Allocation not found"));

        if (!allocation.isActive()) {
            return ResponseEntity.badRequest().body("Allocation already inactive");
        }

        Room room = allocation.getRoom();
        allocation.setActive(false);
        allocationRepository.save(allocation);

        room.setOccupied(room.getOccupied() - 1);
        room.setAvailable(true);
        roomRepository.save(room);

        return ResponseEntity.ok("Room vacated successfully");
    }
}
