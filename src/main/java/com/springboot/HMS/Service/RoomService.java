package com.springboot.HMS.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.springboot.HMS.Entity.Room;
import com.springboot.HMS.dto.RoomRequestDTO;

public interface RoomService {

    ResponseEntity<?> createRoom(RoomRequestDTO req);

    List<Room> getAllRooms();

    ResponseEntity<?> updateRoomCapacity(Long id, int capacity);

    ResponseEntity<String> deleteRoom(Long id);
}
