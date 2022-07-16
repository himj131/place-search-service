package com.himj.placesearchservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.himj.placesearchservice.commons.HttpUtil.get;
@Service
public class KakaoSearchService {
    @Value("${kakao.search.uri}")
    private String searchUri;
    @Value("${kako.searh.client.apikey}")
    private String apiKey;

    @Autowired
    ObjectMapper mapper;
    public KakaoSearchResponse searh(SearchRequest searchRequest) {
        String text = null;
        try {
            text = URLEncoder.encode(searchRequest.getKeyword(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(searchUri)
                .queryParam("query", text)
                .queryParam("size", searchRequest.getSearchCount())
                .build();
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", apiKey);
        String responseBody = get(uriComponents.toString(), requestHeaders);
        try {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
            return mapper.readValue(responseBody, KakaoSearchResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
