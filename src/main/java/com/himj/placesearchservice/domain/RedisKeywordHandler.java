package com.himj.placesearchservice.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class RedisKeywordHandler {
    @Resource(name = "redisTemplate")
    private ZSetOperations<String, String> zSetOperations;

    @EventListener
    public void handleKeywordSearchEvent(KeywordSearchEvent event) {
        zSetOperations.incrementScore("searchKeyword", event.getKeyword(), 1);
    }

    public Set<ZSetOperations.TypedTuple<String>> topKeywords() {
        Set<ZSetOperations.TypedTuple<String>> results = zSetOperations.reverseRangeWithScores("searchKeyword", 0, 9);
        return results;
    }
}
