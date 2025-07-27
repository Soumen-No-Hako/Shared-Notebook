package com.example.sharednotepad.dto;

import com.example.sharednotepad.model.Room;
import lombok.Data;
import java.util.Set;

@Data
public class RoomDetailsDTO {
    private String roomCode;
    private String ownerId;
    private Set<String> members;
    private Set<String> noteIds;
    private java.util.Map<String,String> roles;

    public static RoomDetailsDTO from(Room r) {
        RoomDetailsDTO dto = new RoomDetailsDTO();
        dto.setRoomCode(r.getRoomCode());
        dto.setOwnerId(r.getOwnerId());
        dto.setMembers(r.getMembers());
        dto.setNoteIds(r.getNoteIds());
        dto.setRoles(r.getRoles());
        return dto;
    }
}