package com.example.sharednotepad.service;

import com.example.sharednotepad.model.Note;
import com.example.sharednotepad.repository.NoteRepository;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NoteService {
    private final NoteRepository noteRepo;
    private final RoomService roomService;

    public NoteService(NoteRepository noteRepo, RoomService roomService) {
        this.noteRepo = noteRepo;
        this.roomService = roomService;
    }

    public Note createNote(String name, String ownerId, String roomCode) {
        Note n = new Note();
        n.setName(name);
        n.setOwnerId(ownerId);
        n.setMemberIds(new HashSet<>(List.of(ownerId)));
        n.setData("");
        Note saved = noteRepo.save(n);
        roomService.addNoteToRoom(roomCode, saved.getId());
        return saved;
    }

    public Note updateNoteData(String noteId, String data) {
        Note n = noteRepo.findById(noteId)
                .orElseThrow(() -> new NoSuchElementException("Note not found"));
        n.setData(data);
        return noteRepo.save(n);
    }

    public List<Note> getNotesOfUser(String userId) {
        return noteRepo.findAll().stream()
                .filter(n -> n.getMemberIds().contains(userId))
                .toList();
    }
}