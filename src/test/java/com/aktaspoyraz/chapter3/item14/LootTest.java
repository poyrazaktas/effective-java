package com.aktaspoyraz.chapter3.item14;

import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class LootTest {

    /**
     * The implement or must ensure that {@code sgn(x.compareTo(y))==-sgn(y. compareTo(x))} for all x and y.
     * This implies that {@code x.compareTo(y)} must throw an exception if and only if {@code y.compareTo(x)} throws an exception.
     */
    @Test
    public void shouldObeyTheFirstContract() {
        Loot loot1 = new Loot(2, 3);
        Loot loot2 = new Loot(2, 3);

        assertTrue(Math.signum(loot1.compareTo(loot2)) == -1 * Math.signum(loot2.compareTo(loot1)));
    }


    /**
     * The implementor must also ensure that the relation is transitive:
     * {@code (x. compareTo(y) > 0 && y.compareTo(z) > 0)} implies {@code x.compareTo(z) > 0}.
     */
    @Test
    public void shouldObeyTheSecondContract() {
        Loot loot1 = new Loot(1, 1);
        Loot loot2 = new Loot(1, 2);
        Loot loot3 = new Loot(2, 1);

        assertTrue(loot3.compareTo(loot2) > 0 && loot2.compareTo(loot1) > 0);
        assertTrue(loot3.compareTo(loot1) > 0);
    }

    /**
     * The implementor must ensure that {@code x.compareTo(y)==0} implies that
     * {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for all z.
     */
    @Test
    public void shouldObeyTheThirdContract(){
        Loot x = new Loot(2, 1);
        Loot y = new Loot(2, 1);

        Loot z1 = new Loot(2, 0);
        Loot z2 = new Loot(2, 1);
        Loot z3 = new Loot(2, 3);

        assertTrue(x.compareTo(y) == 0);
        assertTrue(Math.signum(x.compareTo(z1)) == Math.signum(y.compareTo(z1)));
        assertTrue(Math.signum(x.compareTo(z2)) == Math.signum(y.compareTo(z2)));
        assertTrue(Math.signum(x.compareTo(z3)) == Math.signum(y.compareTo(z3)));
    }


    @Test
    public void shouldReturn_0_whenTwoLootsHaveSameAmountOfGoldsAndSilvers() {
        Loot loot1 = new Loot(1, 1);
        Loot loot2 = new Loot(1, 1);

        assert loot1.compareTo(loot2) == 0;
    }

    @Test
    public void shouldReturn_1_whenTheFirstLootHasMoreSilversAndGoldsAreEqual() {
        Loot loot1 = new Loot(1, 2);
        Loot loot2 = new Loot(1, 1);

        assertSame(1, loot1.compareTo(loot2));

    }

    @Test
    public void shouldReturn_1_whenTheFirstLootHasMoreGoldsAndSilversAreEqual() {
        Loot loot1 = new Loot(2, 2);
        Loot loot2 = new Loot(1, 2);

        assertSame(1, loot1.compareTo(loot2));

    }
}