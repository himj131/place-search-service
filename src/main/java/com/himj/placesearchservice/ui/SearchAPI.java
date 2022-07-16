package com.himj.placesearchservice.ui;

import com.himj.placesearchservice.service.SearchRequest;
import com.himj.placesearchservice.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class SearchAPI {
    SearchService searchService;

    public SearchAPI(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchPlace(@RequestParam("keyword") String keyword) throws IOException, InterruptedException {
        SearchRequest req = new SearchRequest(keyword);
        List<String> results = searchService.searchByKeyword(req);
        return ResponseEntity.ok(results);
    }
}
