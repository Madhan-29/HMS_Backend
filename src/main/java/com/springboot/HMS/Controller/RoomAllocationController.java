package com.springboot.HMS.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.springboot.HMS.Service.RoomAllocationService;
import com.springboot.HMS.dto.AllocateRoomRequestDTO;
import com.springboot.HMS.dto.AllocationResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/allocations")
@Validated
public class RoomAllocationController {

    private final RoomAllocationService roomAllocationService;

    public RoomAllocationController(RoomAllocationService roomAllocationService) {
        this.roomAllocationService = roomAllocationService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/assign")
    public ResponseEntity<?> assignRoom(@RequestBody @Valid AllocateRoomRequestDTO req) {
        return roomAllocationService.assignRoom(req);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<AllocationResponseDTO> getAllActiveAllocations() {
        return roomAllocationService.getAllActiveAllocations();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/vacate/{allocationId}")
    public ResponseEntity<String> vacateRoom(@PathVariable Long allocationId) {
        return roomAllocationService.vacateRoom(allocationId);
    }
}
