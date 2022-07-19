package com.himj.placesearchservice.ui;

import com.himj.placesearchservice.service.SearchRequest;
import com.himj.placesearchservice.service.SearchService;
import com.himj.placesearchservice.domain.TopKeyword;
import com.himj.placesearchservice.service.TopSearchKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchAPI {
    private final SearchService searchService;
    private final TopSearchKeywordService topSearchKeywordService;

    @GetMapping("/search")
    public ResponseEntity<SearchResponse> searchPlace(@RequestParam("keyword") String keyword) {
        SearchRequest req = new SearchRequest(keyword);
        List<String> results = searchService.searchByKeyword(req);
        return ResponseEntity.ok(new SearchResponse(results));
    }

    @GetMapping("/search/top-keyword")
    public ResponseEntity<TopKeywordResponse> topSearchKeyword() {
        List<TopKeyword> results = topSearchKeywordService.topKeywords();
        return ResponseEntity.ok(new TopKeywordResponse(results));
    }
}
