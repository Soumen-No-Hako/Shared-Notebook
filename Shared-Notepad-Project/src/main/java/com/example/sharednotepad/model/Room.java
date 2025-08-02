package com.example.sharednotepad.model;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROOM")
public class Room {
    @Id
    private String roomCode;
    private String ownerId;
    private Set<String> noteIds;
    private Set<String> members;
    private Map<String, String> roles; // userId -> role (owner/read/write)
}