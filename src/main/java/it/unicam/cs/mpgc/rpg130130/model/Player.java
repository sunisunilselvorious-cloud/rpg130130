package it.unicam.cs.mpgc.rpg130130.model;

import it.unicam.cs.mpgc.rpg130130.item.Consumable;
import it.unicam.cs.mpgc.rpg130130.item.Inventory;
import it.unicam.cs.mpgc.rpg130130.item.Item;

public class Player extends GameCharacter {
    private final Inventory inventory = new Inventory();

    public Player(String name, int maxHp, int attack) {
        super(name, maxHp, attack);
    }

    @Override
    public int getAttack() {
        return super.getAttack() + inventory.getTotalAttackBonus();
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public boolean use(Consumable consumable) {                       // nuovo metodo
        return inventory.consume(consumable, this);
    }
}


