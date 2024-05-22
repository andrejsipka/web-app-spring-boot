package com.demo.events.event;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class EventRepository {
    private List<Event> events = new ArrayList<>();

    List<Event> findAll() {
        return events;
    }

    Optional<Event> findById(Integer id) {
        return events.stream().filter(event -> Objects.equals(event.id(), id)).findFirst();
    }

    void create(Event event) {
        this.events.add(event);
    }

    void update(Event event, Integer id) {
        Optional<Event> existingEvent = findById(id);

        if(existingEvent.isPresent()) {
            events.set(events.indexOf(existingEvent.get()), event);
        }
    }

    void delete(Integer id) {
        this.events.removeIf(event -> event.id().equals(id));
    }

    @PostConstruct
    private void init() {
        events.add(new Event(1, "At Work", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), "Exchanging time for money", Type.PRIVATE));
        events.add(new Event(2, "At Home", LocalDateTime.now(), LocalDateTime.now().plus(3, ChronoUnit.HOURS), "Sleeping at home", Type.PRIVATE));
    }
}
