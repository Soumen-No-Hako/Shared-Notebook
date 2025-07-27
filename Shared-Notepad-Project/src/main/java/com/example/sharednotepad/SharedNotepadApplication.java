package com.example.sharednotepad;

import com.example.sharednotepad.model.Profile;
import com.example.sharednotepad.model.Room;
import com.example.sharednotepad.model.Note;
import com.example.sharednotepad.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class SharedNotepadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharedNotepadApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(ProfileRepository pr,
                               RoomRepository rr,
                               NoteRepository nr) {
        return args -> {
            pr.deleteAll();
            rr.deleteAll();
            nr.deleteAll();

            Profile alice = new Profile("u1","Alice","alice@example.com","pwd",Set.of());
            Profile bob   = new Profile("u2","Bob","bob@example.com","pwd",Set.of());
            pr.saveAll(List.of(alice,bob));

            Room r = new Room("R1234","u1",
                    new HashSet<>(),
                    new HashSet<>(List.of("u1","u2")),
                    new HashMap<>());
            r.getRoles().put("u1","owner");
            r.getRoles().put("u2","read");
            rr.save(r);

            Note n = new Note(null,"FirstNote","u1",
                    new HashSet<>(List.of("u1","u2")),
                    "");
            n = nr.save(n);
            r.getNoteIds().add(n.getId());
            rr.save(r);
        };
    }
}