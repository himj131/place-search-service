package com.himj.placesearchservice.service;

import com.himj.placesearchservice.common.event.Events;
import com.himj.placesearchservice.domain.KeywordSearchEvent;
import com.himj.placesearchservice.infra.KakaoSearchResponse;
import com.himj.placesearchservice.infra.KakaoSearchService;
import com.himj.placesearchservice.infra.NaverSearchResponse;
import com.himj.placesearchservice.infra.NaverSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class SearchService {
    private final NaverSearchService naverSearchService;
    private final KakaoSearchService kaKaoSearchService;

    @Cacheable(value = "searchedKeywords", key = "#searchRequest.keyword")
    public List<String> searchByKeyword(SearchRequest searchRequest) {
        Events.raise(new KeywordSearchEvent(searchRequest.getKeyword()));
        KakaoSearchResponse kakaoRes = kaKaoSearchService.searh(searchRequest);
        NaverSearchResponse naverRes = naverSearchService.search(searchRequest);

        SearchResult result = new SearchResult(kakaoRes.toCommonType(), naverRes.toCommonType());
        return result.resultKeywords();
    }
}
