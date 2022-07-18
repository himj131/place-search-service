package com.himj.placesearchservice.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.himj.placesearchservice.commons.GeoPoint;
import com.himj.placesearchservice.commons.GeoTrans;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class NaverSearchResponse {
    private int display;
    private List<Item> items;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    static class Item {
        private String title;
        private String mapx;
        private String mapy;
    }

    public List<String> keywordList() {
        if(items == null) return Collections.emptyList();
        return items.stream().map(Item::getTitle).toList();
    }

    public List<SearchCommonResult> toCommonType(){
        if(items == null) return Collections.emptyList();
        return items.stream()
                .map(it -> {
                    GeoPoint point = new GeoPoint(it.mapx, it.mapy);
                    return new SearchCommonResult(it.title, GeoTrans.convert(GeoTrans.KATEC, GeoTrans.GEO, point));
                }).toList();
    }
}
