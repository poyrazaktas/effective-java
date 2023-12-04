package com.aktaspoyraz.chapter3.item10;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CarTest {
    Car polo1 = new Car("volkswagen", "polo", 2022);
    Car polo2 = new Car("volkswagen", "polo", 2022);
    Car polo3 = new Car("volkswagen", "polo", 2022);
    Car porsche = new Car("porsche","taycan",2022);

    @Test
    public void shouldEqualsSymmetric(){

        assertEquals(polo1, polo2);
        assertEquals(polo2, polo1);
        assertNotEquals(polo1, porsche);
        assertNotEquals(polo2, porsche);
    }

    @Test
    public void shouldEqualsTransitive(){

        assertEquals(polo1, polo2);
        assertEquals(polo2, polo3);
        assertEquals(polo1, polo3);

        assertNotEquals(polo1,porsche);
        assertNotEquals(polo2,porsche);
        assertNotEquals(polo3,porsche);

    }

    @Test
    public void shouldEqualsConsistent(){

        for (int i=0;i<100;i++){
            assertEquals(polo1,polo2);
            assertNotEquals(polo1,porsche);
            assertNotEquals(polo2,porsche);
        }

    }

}