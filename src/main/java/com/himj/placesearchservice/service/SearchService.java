package com.himj.placesearchservice.service;

import com.himj.placesearchservice.domain.Events;
import com.himj.placesearchservice.domain.KeywordSearchEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class SearchService {
    private final NaverSearchService naverSearcheService;
    private final KakaoSearchService kaKaoSearchService;

    public List<String> searchByKeyword(SearchRequest searchRequest) {
        Events.raise(new KeywordSearchEvent(searchRequest.getKeyword()));
        NaverSearchResponse naverRes = naverSearcheService.search(searchRequest);
        KakaoSearchResponse kakaoRes = kaKaoSearchService.searh(searchRequest);

        SearchResult result = new SearchResult(kakaoRes.keywordList(), naverRes.keywordList());
        return result.refinedResults();
    }
}
