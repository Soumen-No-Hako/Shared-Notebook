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

}