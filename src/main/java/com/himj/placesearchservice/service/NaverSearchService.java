package com.himj.placesearchservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.himj.placesearchservice.commons.HttpUtils.get;

@Service
public class NaverSearchService {
    @Value("${naver.search.uri}")
    private String searchUri;
    @Value("${naver.search.client.id}")
    private String clientId;
    @Value("${naver.search.client.pwd}")
    private String clientSecret;

    @Autowired
    ObjectMapper mapper;

    public NaverSearchResponse search(SearchRequest requset) {
        String text = null;
        try {
            text = URLEncoder.encode(requset.getKeyword(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }
        String apiURL = searchUri + "query=" + text;
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL, requestHeaders);

        try {
            return mapper.readValue(responseBody, NaverSearchResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
