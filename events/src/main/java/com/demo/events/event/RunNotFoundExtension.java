package com.demo.events.event;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RunNotFoundExtension extends RuntimeException {
    public RunNotFoundExtension() {
        super("Event Not Found");
    }
}
