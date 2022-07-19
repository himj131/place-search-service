package com.himj.placesearchservice.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class NaverSearchService {
    @Value("${naver.search.uri}")
    private String searchUri;
    @Value("${naver.search.client.id}")
    private String clientId;
    @Value("${naver.search.client.pwd}")
    private String clientSecret;

    final ObjectMapper mapper;

    public NaverSearchResponse search(SearchRequest requset) {
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

        UriComponents uriComponents = UriComponentsBuilder.fromUriString(searchUri)
                .queryParam("query", textEncoding(requset.getKeyword()))
                .queryParam("display", requset.getSearchCount())
                .build();
        String responseBody = get(uriComponents.toUriString(), requestHeaders);
        try {
            return mapper.readValue(responseBody, NaverSearchResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
