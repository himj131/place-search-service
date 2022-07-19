package com.himj.placesearchservice.ui;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SearchResponse {
    private List<String> datas;

    public SearchResponse(List<String> results) {
        this.datas = results.stream()
                .map(it -> it.replaceAll("\\<[^>]*>",""))
                .collect(Collectors.toList());
    }
}
