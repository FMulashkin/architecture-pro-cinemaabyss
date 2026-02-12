package ru.cinemaabyss.events.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.cinemaabyss.events.model.Event;

@Service
public class EventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic:cinema.events}")
    private String topic;

    public EventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public <T> void sendEvent(String type, T data) {
        var event = new Event<>(type, data);
        kafkaTemplate.send(topic, type, event);
        System.out.println("[PRODUCER] Отправлено: " + type + " -> " + data);
    }
}