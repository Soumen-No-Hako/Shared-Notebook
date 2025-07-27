package com.example.sharednotepad.dto;

import com.example.sharednotepad.model.Profile;
import com.example.sharednotepad.model.Room;
import com.example.sharednotepad.model.Note;
import lombok.Data;
import java.util.List;

@Data
public class HomeDTO {
    private Profile profile;
    private List<Room> ownedRooms;
    private List<Room> memberRooms;
    private List<Note> notes;
    private List<ActivityDTO> recentActivities;

    public HomeDTO(Profile p, List<Room> rooms, List<Note> notes, List<ActivityDTO> acts) {
        this.profile = p;
        this.ownedRooms = rooms.stream()
                .filter(r -> r.getOwnerId().equals(p.getUserId()))
                .toList();
        this.memberRooms = rooms;
        this.notes = notes;
        this.recentActivities = acts;
    }
}