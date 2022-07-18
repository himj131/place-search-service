package com.himj.placesearchservice.commons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GeoTransTest {

    @Test
    void transferToGeo() {
        GeoPoint naverPoint = new GeoPoint("305231", "547171");
        GeoPoint kakaoPoint = GeoTrans.convert(GeoTrans.KATEC, GeoTrans.GEO, naverPoint);

        GeoPoint expected = new GeoPoint("126.92562192609242", "37.52182482597416");
        assertTrue(expected.sameLocation(kakaoPoint));
    }
}