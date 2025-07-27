package com.example.sharednotepad.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rooms")
public class Room {
    @Id
    private String roomCode;
    private String ownerId;
    private Set<String> noteIds;
    private Set<String> members;
    private Map<String, String> roles; // userId -> role (owner/read/write)
}