package com.aktaspoyraz.chapter4.item17;

public class Point {
    private final double x;
    private final double y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Point from(double x, double y){
        return new Point(x,y);
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this){
            return true;
        }

        if (!(obj instanceof Point)){
            return false;
        }

        Point point = (Point) obj;

        if (point.x == this.x && point.y == this.y){
            return true;
        }

        return false;
    }
}
