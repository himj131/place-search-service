package com.himj.placesearchservice.ui;

import com.himj.placesearchservice.service.SearchRequest;
import com.himj.placesearchservice.service.SearchService;
import com.himj.placesearchservice.service.TopKeyword;
import com.himj.placesearchservice.service.TopSearchKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchAPI {
    private final SearchService searchService;
    private final TopSearchKeywordService topSearchKeywordService;

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchPlace(@RequestParam("keyword") String keyword) {
        SearchRequest req = new SearchRequest(keyword);
        List<String> results = searchService.searchByKeyword(req);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/search/top-keyword")
    public ResponseEntity<List<TopKeyword>> topSearchKeyword() {
        List<TopKeyword> results = topSearchKeywordService.topKeywords();
        return ResponseEntity.ok(results);
    }
}
