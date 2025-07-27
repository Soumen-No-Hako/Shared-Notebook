package com.example.sharednotepad.controller;

import com.example.sharednotepad.dto.CreateRoomRequest;
import com.example.sharednotepad.dto.RoomDetailsDTO;
import com.example.sharednotepad.model.Room;
import com.example.sharednotepad.service.RoomService;
import com.example.sharednotepad.util.CurlPrinter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/room")
public class RoomController {
    private final RoomService svc;

    public RoomController(RoomService svc) {
        this.svc = svc;
    }

    @PostMapping("/create")
    public Room create(@RequestBody CreateRoomRequest req) {
        String endpoint = "http://localhost:8080/app/room/create";
        CurlPrinter.printCurl("POST",endpoint, req);
        return svc.createRoom(req.getRoomCode(), req.getOwnerId());
    }

    @PostMapping("/{roomCode}/join")
    public ResponseEntity<Void> join(@PathVariable String roomCode,
                                     @RequestParam String userId) {
        svc.joinRoom(roomCode, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{roomCode}")
    public RoomDetailsDTO getDetails(@PathVariable String roomCode) {
        return svc.getRoomDetails(roomCode);
    }
}