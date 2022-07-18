package com.himj.placesearchservice.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.himj.placesearchservice.commons.GeoPoint;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class KakaoSearchResponse {
    private Meta meta;
    private List<Document> documents;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    static class Document {
        private String placeName;
        private String x;
        private String y;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    static class Meta {
        private int pageableCount;
    }

    public List<String> keywordList() {
        if(documents == null) return Collections.emptyList();
        return documents.stream()
                .map(KakaoSearchResponse.Document::getPlaceName)
                .toList();
    }

    public List<SearchCommonResult> toCommonType(){
        if(documents == null) return Collections.emptyList();
        return documents.stream()
                .map(it -> new SearchCommonResult(it.placeName, it.x, it.y))
                .toList();
    }
}
