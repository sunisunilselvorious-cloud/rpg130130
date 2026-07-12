package it.unicam.cs.mpgc.rpg130130.item;

public class Item {
    private String name;
    private int bonusAttack;

    public Item(String name, int bonusAttack) {
        this.name = name;
        this.bonusAttack = bonusAttack;
    }

    public int getBonusAttack() {
        return bonusAttack;
    }

    public String getName() {
        return name;
    }
}
