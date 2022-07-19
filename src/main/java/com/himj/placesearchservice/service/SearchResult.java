package com.himj.placesearchservice.service;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {
    private List<SearchCommonResultType> kakaoEngineResults;
    private List<SearchCommonResultType> otherEngineResults;
    private List<SearchCommonResultType> refinedResults;

    private int displayCount;

    public SearchResult(List<SearchCommonResultType> kakaoResults, List<SearchCommonResultType>...otherResults) {
        this.kakaoEngineResults = kakaoResults;
        this.refinedResults = new ArrayList<>();
        this.otherEngineResults = combineOthers(otherResults);
        this.displayCount = 10;
    }

    private List<SearchCommonResultType> combineOthers(List<SearchCommonResultType>[] otherResults) {
        List<SearchCommonResultType> others = new ArrayList<>();
        for(int i=0; i<otherResults.length; i++) {
            others.addAll(otherResults[i]);
        }
        return others;
    }

    private List<SearchCommonResultType> refinedResults() {
        refinedResults.addAll(kakaoEngineResults.size() < 5 ? kakaoEngineResults : kakaoEngineResults.subList(0, 5));
        fillResultsFrom(otherEngineResults);

        if(refinedResults.size() < displayCount) {
            fillResultsFrom(kakaoEngineResults);
        }
        return refinedResults;
    }

    private void fillResultsFrom(List<SearchCommonResultType> fromEnginResults) {
        for(int i = 0; i<fromEnginResults.size(); i++) {
            SearchCommonResultType target = fromEnginResults.get(i);
            if(alreadyExists(target)) continue;
            if(refinedResults.size() == displayCount) break;
            refinedResults.add(target);
        }
    }

    private boolean alreadyExists(SearchCommonResultType target) {
        return refinedResults.stream()
                .anyMatch(it -> it.equals(target));
    }

    public List<String> resultKeywords() {
        return refinedResults().stream().map(it->it.getKeyword()).toList();
    }
}
