package com.example.sharednotepad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;

@Data
@AllArgsConstructor
public class ActivityDTO {
    private String userId;
    private String action;
    private String targetId;
    private Instant timestamp;
}