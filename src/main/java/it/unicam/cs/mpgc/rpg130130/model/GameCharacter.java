package it.unicam.cs.mpgc.rpg130130.model;

public abstract class GameCharacter {
    private final String name;
    private final int maxHp;
    private final int baseAttack;
    private int hp;

    protected GameCharacter(String name, int maxHp, int attack) {
        this(name, maxHp, maxHp, attack);
    }

    protected GameCharacter(String name, int maxHp, int hp, int attack) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = Math.min(hp, maxHp);
        this.baseAttack = attack;
    }

    public void takeDamage(int damage) {
        hp = Math.max(0, hp - Math.max(0, damage));
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + Math.max(0, amount));
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int getAttack() {
        return baseAttack;
    }

    public int getBaseAttack() { return baseAttack; }
    public String getName()    { return name; }
    public int getHp()         { return hp; }
    public int getMaxHp()      { return maxHp; }
}

