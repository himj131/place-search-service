package com.himj.placesearchservice.domain;

import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EventHandler {
    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valusOps;

    @EventListener
    public void handleKeywordSearchEvent(KeywordSearchEvent event) {
        valusOps.increment(event.getKeyword());
    }
}
