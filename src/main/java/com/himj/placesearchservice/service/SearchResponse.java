package com.himj.placesearchservice.service;

import lombok.Getter;

@Getter
public class SearchResponse {
    private String keyword;
    private int searchCount = 10;

    public SearchResponse(String keyword) {
        this.keyword = keyword;
    }
}
