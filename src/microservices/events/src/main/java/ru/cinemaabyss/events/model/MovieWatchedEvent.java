package ru.cinemaabyss.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MovieWatchedEvent(
        @JsonProperty("movie_id") String movieId,
        @JsonProperty("title") String title,
        @JsonProperty("action") String action,
        @JsonProperty("user_id") String userId) {
}
