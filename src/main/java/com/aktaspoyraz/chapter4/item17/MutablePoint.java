package com.aktaspoyraz.chapter4.item17;

public class MutablePoint {
    private double x;
    private double y;

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point getPoint(){
        return Point.from(x,y);
    }

}
