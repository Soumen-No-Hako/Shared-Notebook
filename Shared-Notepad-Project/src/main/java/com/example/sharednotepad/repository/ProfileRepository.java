package com.example.sharednotepad.repository;

import com.example.sharednotepad.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, String> {
    Optional<Profile> findByEmail(String email);
}