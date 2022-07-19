package com.himj.placesearchservice.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.himj.placesearchservice.service.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import static com.himj.placesearchservice.common.HttpUtil.get;
import static com.himj.placesearchservice.common.HttpUtil.textEncoding;

@RequiredArgsConstructor
@Service
public class KakaoSearchService {
    @Value("${kakao.search.uri}")
    private String searchUri;
    @Value("${kako.searh.client.apikey}")
    private String apiKey;

    private final ObjectMapper objectMapper;

    public KakaoSearchResponse search(SearchRequest searchRequest) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(searchUri)
                .queryParam("query", textEncoding(searchRequest.getKeyword()))
                .queryParam("size", searchRequest.getSearchCount())
                .build();
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", apiKey);
        String responseBody = get(uriComponents.toString(), requestHeaders);

        try {
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
            return objectMapper.readValue(responseBody, KakaoSearchResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
