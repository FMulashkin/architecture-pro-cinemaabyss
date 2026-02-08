package ru.cinemaabyss.events.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cinemaabyss.events.producer.EventProducer;
import ru.cinemaabyss.events.model.*;

import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventProducer eventProducer;

    public EventController(EventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    @PostMapping("/user")
    public ResponseEntity<Object> createUser(@RequestBody UserCreatedEvent event) {
        eventProducer.sendEvent("UserCreated", event);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success());
    }

    @PostMapping("/payment")
    public ResponseEntity<Object> processPayment(@RequestBody PaymentProcessedEvent event) {
        eventProducer.sendEvent("PaymentProcessed", event);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success());
    }

    @PostMapping("/movie")
    public ResponseEntity<Object> watchMovie(@RequestBody MovieWatchedEvent event) {
        eventProducer.sendEvent("MovieWatched", event);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success());
    }
}