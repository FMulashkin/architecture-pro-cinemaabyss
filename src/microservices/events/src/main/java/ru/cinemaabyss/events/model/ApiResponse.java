package ru.cinemaabyss.events.model;

public record ApiResponse(String status) {
    public static ApiResponse success() {
        return new ApiResponse("success");
    }
}
