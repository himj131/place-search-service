package com.himj.placesearchservice.service;

import com.himj.placesearchservice.domain.RedisKeywordHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TopSearchKeywordService {
    private final RedisKeywordHandler redisHandler;

    public List<TopKeyword> topKeywords() {
        Set<ZSetOperations.TypedTuple<String>> results = redisHandler.topKeywords();
        if (results.isEmpty()) {
            return Collections.emptyList();
        }
        return results.stream()
                .map(it -> new TopKeyword(it.getValue(), it.getScore().longValue()))
                .toList();
    }
}
