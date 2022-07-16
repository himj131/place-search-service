package com.himj.placesearchservice.service;

import lombok.Getter;

@Getter
public class SearchRequest {
    private String keyword;

    public SearchRequest(String keyword) {
        this.keyword = keyword;
    }
}
