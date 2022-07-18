package com.himj.placesearchservice.service;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {
    private List<SearchCommonResult> kakaoEngineResults;
    private List<SearchCommonResult> otherEngineResults;
    private List<SearchCommonResult> refinedResults;

    private int displayCount;

    public SearchResult(List<SearchCommonResult> kakaoReults, List<SearchCommonResult>...otherResults) {
        this.kakaoEngineResults = kakaoReults;
        this.refinedResults = new ArrayList<>();
        this.otherEngineResults = combineOthers(otherResults);
        this.displayCount = 10;
    }

    private List<SearchCommonResult> combineOthers(List<SearchCommonResult>[] otherResults) {
        List<SearchCommonResult> others = new ArrayList<>();
        for(int i=0; i<otherResults.length; i++) {
            others.addAll(otherResults[i]);
        }
        return others;
    }

    private List<SearchCommonResult> refinedResults() {
        refinedResults.addAll(kakaoEngineResults.size() < 5 ? kakaoEngineResults : kakaoEngineResults.subList(0, 5));
        fillResultsFrom(otherEngineResults);

        if(refinedResults.size() < displayCount) {
            fillResultsFrom(kakaoEngineResults);
        }
        return refinedResults;
    }

    private void fillResultsFrom(List<SearchCommonResult> fromEnginResults) {
        for(int i = 0; i<fromEnginResults.size(); i++) {
            SearchCommonResult target = fromEnginResults.get(i);
            if(alreadyExists(target)) continue;
            if(refinedResults.size() == displayCount) break;
            refinedResults.add(target);
        }
    }

    private boolean alreadyExists(SearchCommonResult target) {
        return refinedResults.stream()
                .anyMatch(it -> it.equals(target));
    }

    public List<String> resultKeywords() {
        return refinedResults().stream().map(it->it.getKeyword()).toList();
    }
}
