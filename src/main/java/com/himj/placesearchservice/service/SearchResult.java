package com.himj.placesearchservice.service;

import org.springframework.beans.factory.annotation.Value;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchResult {
    private List<String> baseEngineResults;
    private List<String> otherEngineResults;
    private List<String> refinedResults;

    @Value("${search.count.limit}")
    private int displayCount;

    public SearchResult(List<String> kakaoReults, List<String>...otherResults) {
        this.baseEngineResults = kakaoReults;
        this.refinedResults = kakaoReults;
        this.otherEngineResults = addDistinct(otherResults);
    }

    private List<String> addDistinct(List<String>[] otherResults) {
        Set<String> distincts = new HashSet<>(otherResults[0]);
        for(int i=1; i<otherResults.length; i++) {
            distincts.addAll(otherResults[i]);
        }
        return distincts.stream().toList();
    }

    public List<String> refinedResults() {
        if(baseEngineResults.size() < displayCount) {
            fillResultsFrom(otherEngineResults, displayCount - baseEngineResults.size());
        }

        if(refinedResults.size() < displayCount) {
            fillResultsFrom(baseEngineResults,displayCount - refinedResults.size());
        }
        return refinedResults;
    }

    private void fillResultsFrom(List<String> fromEnginResults, int needCount) {
        int index= displayCount - needCount;
        while (refinedResults.size() == displayCount) {
            String target = fromEnginResults.get(index);
            if(target == null) break;
            if(refinedResults.contains(target)) continue;
            refinedResults.add(target);
            index++;
        }
    }
}
