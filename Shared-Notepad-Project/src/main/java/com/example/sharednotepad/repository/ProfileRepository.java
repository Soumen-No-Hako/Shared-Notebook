package com.example.sharednotepad.repository;

import com.example.sharednotepad.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ProfileRepository extends MongoRepository<Profile, String> {
    Optional<Profile> findByEmail(String email);
}