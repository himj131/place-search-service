package com.himj.placesearchservice.domain;

public class KeywordSearchEvent {
    private String keyword;

    public KeywordSearchEvent(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
