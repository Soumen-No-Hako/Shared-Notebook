package com.example.sharednotepad.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI sharedNotepadOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Shared Notepad API")
                        .description("Backend API for real-time collaborative notepad"));
    }
}