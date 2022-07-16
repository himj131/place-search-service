package com.himj.placesearchservice.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

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
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    static class Meta {
        private int pageableCount;
    }
}
