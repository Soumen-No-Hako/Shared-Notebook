package com.example.sharednotepad.repository;

import com.example.sharednotepad.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {
}