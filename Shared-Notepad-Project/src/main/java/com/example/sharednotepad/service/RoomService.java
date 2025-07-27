package com.example.sharednotepad.service;

import com.example.sharednotepad.dto.RoomDetailsDTO;
import com.example.sharednotepad.model.Room;
import com.example.sharednotepad.repository.RoomRepository;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.*;

@Service
public class RoomService {
    private final RoomRepository roomRepo;
    private static final String ALPHANUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final SecureRandom rnd = new SecureRandom();

    public RoomService(RoomRepository roomRepo) {
        this.roomRepo = roomRepo;
    }

    public Room createRoom(String requestedCode, String ownerId) {
        String code = (requestedCode != null && !requestedCode.isBlank())
                ? requestedCode
                : generateUniqueCode();

        if (roomRepo.existsByRoomCode(code)) {
            throw new IllegalArgumentException("Room code already exists");
        }

        Room r = new Room();
        r.setRoomCode(code);
        r.setOwnerId(ownerId);
        r.setMembers(new HashSet<>(List.of(ownerId)));
        r.setRoles(Map.of(ownerId, "owner"));
        r.setNoteIds(new HashSet<>());
        return roomRepo.save(r);
    }

    private String generateUniqueCode() {
        String code;
        do {
            var sb = new StringBuilder(5);
            for (int i = 0; i < 5; i++) {
                sb.append(ALPHANUM.charAt(rnd.nextInt(ALPHANUM.length())));
            }
            code = sb.toString();
        } while (roomRepo.existsByRoomCode(code));
        return code;
    }

    public void joinRoom(String roomCode, String userId) {
        Room r = roomRepo.findByRoomCode(roomCode)
                .orElseThrow(() -> new NoSuchElementException("Room not found"));
        r.getMembers().add(userId);
        r.getRoles().put(userId, "read");
        roomRepo.save(r);
    }

    public RoomDetailsDTO getRoomDetails(String roomCode) {
        Room r = roomRepo.findByRoomCode(roomCode)
                .orElseThrow(() -> new NoSuchElementException("Room not found"));
        return RoomDetailsDTO.from(r);
    }

    public List<Room> getRoomsOfUser(String userId) {
        return roomRepo.findAll().stream()
                .filter(r -> r.getMembers().contains(userId))
                .toList();
    }

    public void addNoteToRoom(String roomCode, String noteId) {
        Room r = roomRepo.findByRoomCode(roomCode)
                .orElseThrow(() -> new NoSuchElementException("Room not found"));
        r.getNoteIds().add(noteId);
        roomRepo.save(r);
    }
}