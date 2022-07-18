package com.himj.placesearchservice.service;

import org.springframework.beans.factory.annotation.Value;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchResult {
    private List<SearchCommonResult> baseEngineResults;
    private List<SearchCommonResult> otherEngineResults;
    private List<SearchCommonResult> refinedResults;

    @Value("${search.count.limit}")
    private int displayCount;

    public SearchResult(List<SearchCommonResult> kakaoReults, List<SearchCommonResult>...otherResults) {
        this.baseEngineResults = kakaoReults;
        this.refinedResults = kakaoReults;
        this.otherEngineResults = addDistinct(otherResults);
    }

    private List<SearchCommonResult> addDistinct(List<SearchCommonResult>[] otherResults) {
        Set<SearchCommonResult> distincts = new HashSet<>(otherResults[0]);
        for(int i=1; i<otherResults.length; i++) {
            distincts.addAll(otherResults[i]);
        }
        return distincts.stream().toList();
    }

    public List<SearchCommonResult> refinedResults() {
        if(baseEngineResults.size() < displayCount) {
            fillResultsFrom(otherEngineResults, displayCount - baseEngineResults.size());
        }

        if(refinedResults.size() < displayCount) {
            fillResultsFrom(baseEngineResults,displayCount - refinedResults.size());
        }
        return refinedResults;
    }

    private void fillResultsFrom(List<SearchCommonResult> fromEnginResults, int needCount) {
        int index= displayCount - needCount;
        while (refinedResults.size() == displayCount) {
            SearchCommonResult target = fromEnginResults.get(index);
            if(target == null) break;
            if(alreadyExists(target)) continue;
            refinedResults.add(target);
            index++;
        }
    }

    private boolean alreadyExists(SearchCommonResult target) {
        return refinedResults.stream()
                .anyMatch(it -> it.equals(target));
    }

    public List<String> resultKeywords() {
        return refinedResults.stream().map(it->it.getKeyword()).toList();
    }
}
