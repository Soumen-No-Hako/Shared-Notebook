package com.example.sharednotepad.controller;

import com.example.sharednotepad.dto.CreateNoteRequest;
import com.example.sharednotepad.dto.UpdateNoteRequest;
import com.example.sharednotepad.model.Note;
import com.example.sharednotepad.service.NoteService;
import com.example.sharednotepad.util.CurlPrinter;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/note")
public class NoteController {
    private final NoteService svc;
    private final SimpMessagingTemplate msgs;

    public NoteController(NoteService svc, SimpMessagingTemplate msgs) {
        this.svc  = svc;
        this.msgs = msgs;
    }

    @PostMapping("/create")
    public Note create(@RequestBody CreateNoteRequest req) {
        String endpoint = "http://localhost:8080/app/note/create";
        CurlPrinter.printCurl("POST",endpoint, req);
        return svc.createNote(req.getName(), req.getOwnerId(), req.getRoomCode());
    }

    @PostMapping("/{noteId}/update")
    public ResponseEntity<Void> update(@PathVariable String noteId,
                                       @RequestBody UpdateNoteRequest req) {
        svc.updateNoteData(noteId, req.getData());
        // broadcast to subscribers
        msgs.convertAndSend("/topic/notes/" + noteId, req.getData());
        String endpoint = "http://localhost:8080/app/note/"+noteId+"/update";
        CurlPrinter.printCurl("POST",endpoint, req);
        return ResponseEntity.ok().build();
    }
}