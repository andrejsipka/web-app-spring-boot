package com.demo.events.event;

import com.demo.events.EventsApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {
    private static final Logger log = LoggerFactory.getLogger(EventsApplication.class);
    private final JdbcClient jdbcClient;

    public EventRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Event> findAll() {
        return jdbcClient.sql("select * from event")
                .query(Event.class)
                .list();
    }

    public Optional<Event> findById(Integer id) {
        return jdbcClient.sql("SELECT id, title, startingAt, finishesAt, description, location FROM Event WHERE id = :id")
                .param("id", id)
                .query(Event.class)
                .optional();
    }

    public void create(Event event) {
        var updated = jdbcClient.sql("INSERT INTO Event(id,title, startingAt, finishesAt, description, location) values(?, ?, ?, ?, ?, ?)")
                .params(List.of(event.id(), event.title(), event.startingAt(), event.finishesAt(), event.description(), event.location().toString()))
                .update();

        Assert.state(updated == 1, "Failed to create run " + event.title());
    }


    public void update(Event event, Integer id) {
        var updated = jdbcClient.sql("update run set title = ?, startingAt = startingAt = ?, finishesAt = ?, description = ?, location = ?, where id = ?")
                .params(List.of(event.title(), event.startingAt(), event.finishesAt(), event.description(), event.location().toString(), id))
                .update();

        Assert.state(updated == 1, "Failed to update run " + event.title());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("delete from run where id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete run " + id);

    }

//    private List<Event> events = new ArrayList<>();
//
//    List<Event> findAll() {
//        return events;
//    }
//
//    Optional<Event> findById(Integer id) {
//        return events.stream().filter(event -> Objects.equals(event.id(), id)).findFirst();
//    }
//
//    void create(Event event) {
//        this.events.add(event);
//    }
//
//    void update(Event event, Integer id) {
//        Optional<Event> existingEvent = findById(id);
//
//        if(existingEvent.isPresent()) {
//            events.set(events.indexOf(existingEvent.get()), event);
//        }
//    }
//
//    void delete(Integer id) {
//        this.events.removeIf(event -> event.id().equals(id));
//    }
//
//    @PostConstruct
//    private void init() {
//        events.add(new Event(1, "At Work", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), "Exchanging time for money", Type.PRIVATE));
//        events.add(new Event(2, "At Home", LocalDateTime.now(), LocalDateTime.now().plus(3, ChronoUnit.HOURS), "Sleeping at home", Type.PRIVATE));
//    }
}
