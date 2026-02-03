package com.springboot.HMS.ServiceImplementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.HMS.Entity.Room;
import com.springboot.HMS.Repository.RoomRepository;
import com.springboot.HMS.Service.RoomService;
import com.springboot.HMS.dto.RoomRequestDTO;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public ResponseEntity<?> createRoom(RoomRequestDTO req) {

        if (roomRepository.findByRoomNumber(req.getRoomNumber()).isPresent()) {
            return ResponseEntity.badRequest().body("Room already exists");
        }

        Room room = new Room();
        room.setRoomNumber(req.getRoomNumber());
        room.setCapacity(req.getCapacity());
        room.setOccupied(0);
        room.setAvailable(true);

        return ResponseEntity.ok(roomRepository.save(room));
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public ResponseEntity<?> updateRoomCapacity(Long id, int capacity) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (capacity < room.getOccupied()) {
            return ResponseEntity.badRequest().body("Capacity cannot be less than occupied beds");
        }

        room.setCapacity(capacity);
        room.setAvailable(room.getOccupied() < room.getCapacity());

        return ResponseEntity.ok(roomRepository.save(room));
    }

    @Override
    public ResponseEntity<String> deleteRoom(Long id) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (room.getOccupied() > 0) {
            return ResponseEntity.badRequest().body("Cannot delete room, students are allocated");
        }

        roomRepository.deleteById(id);
        return ResponseEntity.ok("Room deleted successfully");
    }
}
