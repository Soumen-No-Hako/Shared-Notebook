package com.example.sharednotepad.repository;

import com.example.sharednotepad.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, String> {
}