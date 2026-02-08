package ru.cinemaabyss.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentProcessedEvent(
        @JsonProperty("payment_id") String paymentId,
        @JsonProperty("user_id") String userId,
        @JsonProperty("amount") String amount,
        @JsonProperty("status") String status,
        @JsonProperty("timestamp") String timestamp,
        @JsonProperty("method_type") String methodType) {}
