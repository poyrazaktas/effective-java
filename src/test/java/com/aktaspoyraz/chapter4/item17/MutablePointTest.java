package com.aktaspoyraz.chapter4.item17;

import org.junit.Test;

import static org.junit.Assert.*;

public class MutablePointTest {

    @Test
    public void getPointTest(){
        MutablePoint mutablePoint = new MutablePoint();
        mutablePoint.setX(2);
        mutablePoint.setY(5);

        assertEquals(mutablePoint.getPoint(), Point.from(2,5));

        mutablePoint.setY(10);
        mutablePoint.setX(10);

        assertEquals(mutablePoint.getPoint(), Point.from(10,10));
    }

}