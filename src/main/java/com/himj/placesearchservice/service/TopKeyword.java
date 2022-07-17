package com.himj.placesearchservice.service;

import lombok.Getter;

@Getter
public class TopKeyword {
    private String keyword;
    private long searchCnt;

    public TopKeyword(String keyword, long searchCnt) {
        this.keyword = keyword;
        this.searchCnt = searchCnt;
    }
}
