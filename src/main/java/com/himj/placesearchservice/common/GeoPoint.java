package com.himj.placesearchservice.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GeoPoint {
    double x;
    double y;
    double z;
    /**
     *
     */
    public GeoPoint() {
        super();
    }
    /**
     * @param x
     * @param y
     */
    public GeoPoint(double x, double y) {
        super();
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public GeoPoint(String x, String y) {
        super();
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
        this.z = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean sameLocation(GeoPoint point) {
        BigDecimal thisx = new BigDecimal(this.x).setScale(3, RoundingMode.FLOOR);
        BigDecimal thisy = new BigDecimal(this.y).setScale(3, RoundingMode.FLOOR);
        BigDecimal paramx = new BigDecimal(point.x).setScale(3, RoundingMode.FLOOR);
        BigDecimal paramy = new BigDecimal(this.y).setScale(3, RoundingMode.FLOOR);
        return thisx.equals(paramx) && thisy.equals(paramy);
    }
}
