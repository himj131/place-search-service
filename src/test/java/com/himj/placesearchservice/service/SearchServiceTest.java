package com.himj.placesearchservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class SearchServiceTest {
    private SearchService searchService;
    @Autowired
    private NaverSearchService naverSearchService;
    @Autowired
    private KakaoSearchService kakaoSearchService;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @BeforeEach
    void setUp() {
        searchService = new SearchService(naverSearchService, kakaoSearchService);
    }

    @Test
    @DisplayName("검색 조회결과가 캐싱 된다.")
    void chacingSearchedKeywords() {
        //given 캐싱데이터 초기화
        Objects.requireNonNull(redisCacheManager.getCache("searchedKeywords")).clear();
        //given 캐싱데이터 없음
        assertNull(redisCacheManager.getCache("searchedKeywords").get("moon"));

        //when 떡볶이 키워드로 한 번 검색
        searchService.searchByKeyword(new SearchRequest("moon"));

        //then 캐싱 데이터 생김
        assertNotNull(redisCacheManager.getCache("searchedKeywords"));
    }
}