package com.himj.placesearchservice.service;

import com.himj.placesearchservice.common.GeoPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchResultTest {
    private SearchResult searchResult;

    @Test
    @DisplayName("카카오결과 5개 네이버 결과가 5개일 경우 서로 다른 키워드 10개 출력")
    void distinctTenResults() {
        List<SearchCommonResultType> kakao = getSearchResults(1, 5);
        List<SearchCommonResultType> naver = getSearchResults(6, 10);
        searchResult = new SearchResult(kakao, naver);
        List<String> results = searchResult.resultKeywords();
        assertEquals(10, results.size());
    }

    @Test
    @DisplayName("카카오, 네이버 중복결과 2개 있을때, 카카오 5개 -> 네이버 3개 -> 카카오 2개 순으로 노출")
    void duplicatedResults() {
        List<SearchCommonResultType> kakao = getSearchResults(11, 20);
        List<SearchCommonResultType> naver = getSearchResults(8, 12);
        searchResult = new SearchResult(kakao, naver);
        List<String> results = searchResult.resultKeywords();
        assertEquals(results.get(0), "키워드11");
        assertEquals(results.get(1), "키워드12");
        assertEquals(results.get(2), "키워드13");
        assertEquals(results.get(3), "키워드14");
        assertEquals(results.get(4), "키워드15");
        assertEquals(results.get(5), "키워드8");
        assertEquals(results.get(6), "키워드9");
        assertEquals(results.get(7), "키워드10");
        assertEquals(results.get(8), "키워드16");
        assertEquals(results.get(9), "키워드17");
    }

    @Test
    @DisplayName("카카오 2개, 네이버 4개 서로다른 결과 -> 총  6개 노출")
    void savenDistinctResults() {
        List<SearchCommonResultType> kakao = getSearchResults(1, 2);
        List<SearchCommonResultType> naver = getSearchResults(11, 14);
        searchResult = new SearchResult(kakao, naver);
        List<String> results = searchResult.resultKeywords();
        assertEquals(results.size(), 6);
    }

    @Test
    @DisplayName("카카오 10개, 네이버 5개 모두 중복일 경우 카카오 결과 10개 노출")
    void fiveNaverDuplicatedResults() {
        List<SearchCommonResultType> kakao = getSearchResults(1, 10);
        List<SearchCommonResultType> naver = getSearchResults(6, 10);
        searchResult = new SearchResult(kakao, naver);
        List<String> results = searchResult.resultKeywords();
        assertEquals(results.size(), 10);
        assertEquals(results.get(0), kakao.get(0).getKeyword());
        assertEquals(results.get(9), kakao.get(9).getKeyword());
    }

    @Test
    @DisplayName("카카오 0개, 네이버 5개 경우 네이버 결과 5개 노출")
    void zeroKaKaroResults() {
        List<SearchCommonResultType> naver = getSearchResults(1, 5);
        searchResult = new SearchResult(Collections.emptyList(), naver);
        List<String> results = searchResult.resultKeywords();
        assertEquals(results.size(), 5);
    }

    @Test
    @DisplayName("카카오 5개, 네이버 0개 경우 카카오 결과 5개 노출")
    void zeroNaverResults() {
        List<SearchCommonResultType> kakao = getSearchResults(1, 5);
        searchResult = new SearchResult(kakao, Collections.emptyList());
        List<String> results = searchResult.resultKeywords();
        assertEquals(results.size(), 5);
    }

    @Test
    @DisplayName("카카오, 네이버 모두 결과 없는 경우 빈 리스트")
    void zeroAllResults() {
        searchResult = new SearchResult(Collections.emptyList(), Collections.emptyList());
        List<String> results = searchResult.resultKeywords();
        assertEquals(results.size(), 0);
    }

    private List<SearchCommonResultType> getSearchResults(int from, int to) {
        List<SearchCommonResultType> results = new ArrayList<>();
        for(int i=from; i<= to; i++) {
            results.add(new SearchCommonResultType("키워드" + i, new GeoPoint(i, i)));
        }
        return results;
    }
}