package com.himj.placesearchservice.infra;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.himj.placesearchservice.common.GeoPoint;
import com.himj.placesearchservice.common.GeoTrans;
import com.himj.placesearchservice.service.SearchCommonResultType;
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

    public List<SearchCommonResultType> toCommonType(){
        if(items == null) return Collections.emptyList();
        return items.stream()
                .map(it -> {
                    GeoPoint point = new GeoPoint(it.mapx, it.mapy);
                    return new SearchCommonResultType(it.title, GeoTrans.convert(GeoTrans.KATEC, GeoTrans.GEO, point));
                }).toList();
    }
}
