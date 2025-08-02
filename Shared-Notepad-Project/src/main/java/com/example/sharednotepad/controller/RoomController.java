package com.example.sharednotepad.controller;

import com.example.sharednotepad.dto.CreateRoomRequest;
import com.example.sharednotepad.dto.RoomDetailsDTO;
import com.example.sharednotepad.model.Room;
import com.example.sharednotepad.service.RoomService;
import com.example.sharednotepad.util.CurlPrinter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/room")
@Tag(name = "All Room information", description = "All Room URI activity is controlled by this links")
public class RoomController {
    private final RoomService svc;

    public RoomController(RoomService svc) {
        this.svc = svc;
    }

    @PostMapping("/create")
    @Operation(summary = "Room creation", description = "URI for new Room creation. Custom room code or randomized")
    public Room create(@RequestBody CreateRoomRequest req) {
        String endpoint = "http://localhost:8080/app/room/create";
        CurlPrinter.printCurl("POST",endpoint, req);
        return svc.createRoom(req.getRoomCode(), req.getOwnerId());
    }

    @PostMapping("/{roomCode}/join")
    @Operation(summary = "Room Joining", description = "URI for new Room joining shared by room code")
    public ResponseEntity<Void> join(@PathVariable String roomCode,
                                     @RequestParam String userId) {
        svc.joinRoom(roomCode, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{roomCode}")
    @Operation(summary = "Room landing page", description = "URI for visiting the room. Should be redirected")
    public RoomDetailsDTO getDetails(@PathVariable String roomCode) {
        return svc.getRoomDetails(roomCode);
    }
}