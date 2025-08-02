package com.example.sharednotepad.model;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NOTE")
public class Note {
    @Id
    @Column(name = "note_id")
    private String id;
    private String name;
    private String ownerId;
    private Set<String> memberIds;
    private String data;
}