package com.himj.placesearchservice.commons;

import com.himj.placesearchservice.domain.Events;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventInitializer {

    private final ApplicationContext context;

    public EventInitializer(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public InitializingBean initEvent() {
        return () -> Events.setEventPublisher(context);
    }
}
