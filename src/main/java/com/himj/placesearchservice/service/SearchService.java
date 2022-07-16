package com.himj.placesearchservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class SearchService {
    private final NaverSearchService naverSearcheService;
    private final KakaoSearchService kaKaoSearchService;

    public List<String> searchByKeyword(SearchRequest searchRequest) {
        List<String> results = new ArrayList<>();
        NaverSearchResponse naverResponse = naverSearcheService.search(searchRequest);
        KakaoSearchResponse kakaoResponse = kaKaoSearchService.searh(searchRequest);
        results.addAll(kakaoResponse.getDocuments().stream().map(KakaoSearchResponse.Document::getPlaceName).toList());
        results.addAll(naverResponse.getItems().stream().map(NaverSearchResponse.Item::getTitle).toList());
        return results;
    }
}
