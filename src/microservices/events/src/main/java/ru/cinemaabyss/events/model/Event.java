package ru.cinemaabyss.events.model;

public record Event<T>(String type, T data) {}
