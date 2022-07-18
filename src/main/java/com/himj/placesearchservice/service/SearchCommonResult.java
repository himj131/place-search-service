package com.himj.placesearchservice.service;

import com.himj.placesearchservice.commons.GeoPoint;
import lombok.Getter;

import java.util.Objects;

@Getter
public class SearchCommonResult {
    private String keyword;
    private String trimedKeyword;
    private GeoPoint geoPoint;

    public SearchCommonResult(String keyword, GeoPoint geoPoint) {
        this.keyword = keyword;
        this.trimedKeyword = keyword.trim();
        this.geoPoint = geoPoint;
    }

    public SearchCommonResult(String keyword, String x, String y) {
        this.keyword = keyword;
        this.trimedKeyword = keyword.trim();
        this.geoPoint = new GeoPoint(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchCommonResult that = (SearchCommonResult) o;
        return geoPoint.sameLocation(that.geoPoint);
    }
}
