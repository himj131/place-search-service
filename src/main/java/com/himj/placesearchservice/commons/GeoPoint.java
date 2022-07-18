package com.himj.placesearchservice.commons;

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
        return String.format("%.2f", this.x).equals(String.format("%.2f", point.x))
                &&  String.format("%.2f", this.y).equals(String.format("%.2f", point.y));
    }
}
