package ru.cinemaabyss.events.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.cinemaabyss.events.model.Event;

@Service
public class EventConsumer {

    private static final Logger log = LoggerFactory.getLogger(EventConsumer.class);

    @KafkaListener(topics = "${kafka.topic:cinema.events}", groupId = "events-group")
    public void listen(Event<?> event) {
        log.info("[CONSUMER] Получено событие: type={}, data={}", event.type(), event.data());
    }
}