package com.springboot.HMS.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.springboot.HMS.dto.AllocateRoomRequestDTO;
import com.springboot.HMS.dto.AllocationResponseDTO;

public interface RoomAllocationService {

    ResponseEntity<?> assignRoom(AllocateRoomRequestDTO req);

    List<AllocationResponseDTO> getAllActiveAllocations();

    ResponseEntity<String> vacateRoom(Long allocationId);
}
