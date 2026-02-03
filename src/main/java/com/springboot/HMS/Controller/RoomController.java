package com.springboot.HMS.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.springboot.HMS.Entity.Room;
import com.springboot.HMS.Service.RoomService;
import com.springboot.HMS.dto.RoomRequestDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rooms")
@Validated
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody @Valid RoomRequestDTO req) {
        return roomService.createRoom(req);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoomCapacity(@PathVariable Long id, @RequestParam int capacity) {
        return roomService.updateRoomCapacity(id, capacity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        return roomService.deleteRoom(id);
    }
}
