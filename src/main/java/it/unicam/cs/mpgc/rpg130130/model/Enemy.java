package it.unicam.cs.mpgc.rpg130130.model;

public class Enemy {
    private String name;
    private int hp;
    private int attack;

    public Enemy(String name, int hp, int attack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
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
        return attack;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

}
