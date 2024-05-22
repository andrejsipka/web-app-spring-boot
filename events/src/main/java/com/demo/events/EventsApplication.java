package com.demo.events;

import com.demo.events.event.Event;
import com.demo.events.event.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class EventsApplication {
	private static final Logger log = LoggerFactory.getLogger(EventsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EventsApplication.class, args);
	}

	@Bean
	CommandLineRunner events() {
		return args -> {
			Event event = new Event(1, "At Work", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), "Exchanging time for money", Type.PRIVATE);

			log.info("Event " + event);
		};
	}
}
