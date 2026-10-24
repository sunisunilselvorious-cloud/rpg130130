package it.unicam.cs.mpgc.rpg130130.item;

import it.unicam.cs.mpgc.rpg130130.model.GameCharacter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory {
    private final List<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }

    public int getTotalAttackBonus() {
        return items.stream()
                .filter(Equipment.class::isInstance)
                .map(Equipment.class::cast)
                .mapToInt(Equipment::bonusAttack)
                .sum();
    }

    public boolean consume(Consumable consumable, GameCharacter target) {
        if (!items.remove(consumable)) {
            return false;
        }
        consumable.use(target);
        return true;
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }
}