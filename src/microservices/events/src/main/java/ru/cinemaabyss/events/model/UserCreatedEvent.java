package ru.cinemaabyss.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserCreatedEvent(
        @JsonProperty("user_id") String userId,
        @JsonProperty("username") String username,
        @JsonProperty("action") String action,
        @JsonProperty("timestamp") String timestamp) {}
