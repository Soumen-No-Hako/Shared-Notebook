package com.example.sharednotepad.controller;

import com.example.sharednotepad.dto.CreateNoteRequest;
import com.example.sharednotepad.dto.UpdateNoteRequest;
import com.example.sharednotepad.model.Note;
import com.example.sharednotepad.service.NoteService;
import com.example.sharednotepad.util.CurlPrinter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/note")
@Tag(name = "URI for Notes", description = "URIs for all Note creations and Updates")
public class NoteController {
    private final NoteService svc;
    private final SimpMessagingTemplate msgs;

    public NoteController(NoteService svc, SimpMessagingTemplate msgs) {
        this.svc  = svc;
        this.msgs = msgs;
    }

    @PostMapping("/create")
    @Operation(summary = "URI for notebook creation", description = "This endpoint creates new Notebook in a room with room owner")
    public Note create(@RequestBody CreateNoteRequest req) {
        String endpoint = "http://localhost:8080/app/note/create";
        CurlPrinter.printCurl("POST",endpoint, req);
        return svc.createNote(req.getName(), req.getOwnerId(), req.getRoomCode());
    }

    @PostMapping("/{noteId}/update")
    @Operation(summary = "URI for notebook updates", description = "This endpoint edits/updates existing Notebook in a room by people with write privillages")
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