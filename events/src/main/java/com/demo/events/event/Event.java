package com.demo.events.event;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record Event(
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startingAt,
        LocalDateTime finishesAt,
        String description,
        Location location
) {
    // Possible to add validation here!
}
