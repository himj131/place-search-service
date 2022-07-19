package com.himj.placesearchservice.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

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

    public List<SearchCommonResultType> toCommonType(){
        if(documents == null) return Collections.emptyList();
        return documents.stream()
                .map(it -> new SearchCommonResultType(it.placeName, it.x, it.y))
                .toList();
    }
}
