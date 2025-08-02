package com.example.sharednotepad.controller;

import com.example.sharednotepad.dto.HomeDTO;
import com.example.sharednotepad.dto.ActivityDTO;
import com.example.sharednotepad.model.Profile;
import com.example.sharednotepad.service.ProfileService;
import com.example.sharednotepad.service.RoomService;
import com.example.sharednotepad.service.NoteService;
import com.example.sharednotepad.util.CurlPrinter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/app")
@Tag(name="Home Page",description = "Home Page containing all DTOs")
public class AppController {
    private final ProfileService profileSvc;
    private final RoomService roomSvc;
    private final NoteService noteSvc;

    public AppController(ProfileService ps, RoomService rs, NoteService ns) {
        this.profileSvc = ps;
        this.roomSvc    = rs;
        this.noteSvc    = ns;
    }

    @GetMapping("/home")
    @Operation(summary = "Home/Landing page", description = "Any user after login should get information from here")
    public HomeDTO home(@RequestParam String userId) {
        Profile p = profileSvc.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        var rooms = roomSvc.getRoomsOfUser(userId);
        var notes = noteSvc.getNotesOfUser(userId);
        // stub recent activities
        List<ActivityDTO> acts = List.of(
                new ActivityDTO(userId, "joined_room", rooms.get(0).getRoomCode(), Instant.now())
        );
        String endpoint = "http://localhost:8080/app/home";
        CurlPrinter.printCurl("GET",endpoint);
        return new HomeDTO(p, rooms, notes, acts);
    }
}