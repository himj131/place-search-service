package com.himj.placesearchservice.service;

import com.himj.placesearchservice.common.GeoPoint;
import lombok.Getter;

@Getter
public class SearchCommonResultType {
    private String keyword;
    private String trimedKeyword;
    private GeoPoint geoPoint;

    public SearchCommonResultType(String keyword, GeoPoint geoPoint) {
        this.keyword = keyword;
        this.trimedKeyword = keyword.trim();
        this.geoPoint = geoPoint;
    }

    public SearchCommonResultType(String keyword, String x, String y) {
        this.keyword = keyword;
        this.trimedKeyword = keyword.trim();
        this.geoPoint = new GeoPoint(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchCommonResultType that = (SearchCommonResultType) o;
        return geoPoint.sameLocation(that.geoPoint);
    }
}
