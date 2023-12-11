package com.aktaspoyraz.chapter3.item14;

import java.util.Comparator;

public class Loot implements Comparable<Loot>{
    private final int gold;
    private final int silver;

    public Loot(int gold, int silver) {
        this.gold = gold;
        this.silver = silver;
    }


    @Override
    public int compareTo(Loot loot) {
        return Comparator.comparingInt((Loot l) -> l.gold)
                .thenComparing((Loot l) -> l.silver)
                .compare(this,loot);
    }
}
