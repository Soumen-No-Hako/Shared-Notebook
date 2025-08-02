package com.example.sharednotepad.model;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PROFILE")
public class Profile {
    @Id
    private String userId;
    private String name;
    private String email;
    private String password;
    private Set<String> roomIds;
}