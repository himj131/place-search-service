package com.himj.placesearchservice.service;

import lombok.Getter;

@Getter
public class SearchRequest {
    private String keyword;
    private int searchCount = 10;

    public SearchRequest(String keyword) {
        this.keyword = keyword;
    }
}
