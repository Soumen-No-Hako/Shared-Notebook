package com.example.sharednotepad.service;

import com.example.sharednotepad.model.Profile;
import com.example.sharednotepad.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository repo;

    public ProfileService(ProfileRepository repo) {
        this.repo = repo;
    }

    public Profile register(Profile p) {
        return repo.save(p);
    }

    public Optional<Profile> findById(String userId) {
        return repo.findById(userId);
    }

    public Optional<Profile> findByEmail(String email) {
        return repo.findByEmail(email);
    }
}