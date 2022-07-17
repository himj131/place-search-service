package com.himj.placesearchservice.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class NaverSearchResponse {
    private int display;
    private List<Item> items;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    static class Item {
        private String title;
        private String category;
    }

    public List<String> keywordList() {
        if(items == null) return Collections.emptyList();
        return items.stream().map(Item::getTitle).toList();
    }
}
