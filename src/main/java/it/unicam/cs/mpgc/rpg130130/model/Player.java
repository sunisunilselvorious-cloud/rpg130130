package it.unicam.cs.mpgc.rpg130130.model;

import it.unicam.cs.mpgc.rpg130130.item.Inventory;

public class Player {
    private String name;
    private int hp;
    private int attack;
    private Inventory inventory;

    public Player(String name, int hp, int attack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.inventory = new Inventory();
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int getAttack() {
        return attack + inventory.getTotalBonus();
    }

    public int getHp() {
        return hp;
    }
    public void addItem(it.unicam.cs.mpgc.rpg130130.item.Item item) {
        inventory.addItem(item);
    }
    public String getName() {
        return name;
    }
}
