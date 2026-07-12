package it.unicam.cs.mpgc.rpg130130.item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public int getTotalBonus() {
        int total = 0;
        for (Item item : items) {
            total += item.getBonusAttack();
        }
        return total;
    }
}
