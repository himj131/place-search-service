package com.himj.placesearchservice.common.event;

import org.springframework.context.ApplicationEventPublisher;

import java.util.Objects;

public class Events {
    private static ApplicationEventPublisher eventPublisher;

    public static void setEventPublisher(ApplicationEventPublisher eventPublisher) {
        Events.eventPublisher = eventPublisher;
    }

    public static void raise(Object event) {
        if(Objects.nonNull(eventPublisher)) {
            eventPublisher.publishEvent(event);
        }
    }

    public static ApplicationEventPublisher getEventPublisher() {
        return eventPublisher;
    }
}