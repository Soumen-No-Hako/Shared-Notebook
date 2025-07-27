package com.example.sharednotepad.dto;

import lombok.Data;

@Data
public class CreateRoomRequest {
    private String roomCode;
    private String ownerId;
}