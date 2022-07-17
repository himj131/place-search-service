package com.himj.placesearchservice.service;

import lombok.Getter;

@Getter
public class TopKeyword {
    private final String keyword;
    private final long searchCnt;

    public TopKeyword(String keyword, long searchCnt) {
        this.keyword = keyword;
        this.searchCnt = searchCnt;
    }
}
