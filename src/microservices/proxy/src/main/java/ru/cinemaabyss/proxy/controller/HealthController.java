package ru.cinemaabyss.proxy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "success");
    }
    @GetMapping("/api/proxy/health")
    public Map<String, String> healthCheck() {
        return Map.of("status", "success");
    }
}