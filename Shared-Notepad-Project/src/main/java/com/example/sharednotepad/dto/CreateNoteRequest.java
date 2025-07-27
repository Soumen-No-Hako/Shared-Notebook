package com.example.sharednotepad.dto;

import lombok.Data;

@Data
public class CreateNoteRequest {
    private String name;
    private String ownerId;
    private String roomCode;
}