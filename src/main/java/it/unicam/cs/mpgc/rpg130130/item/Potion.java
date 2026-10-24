package it.unicam.cs.mpgc.rpg130130.item;

import it.unicam.cs.mpgc.rpg130130.model.GameCharacter;

public record Potion(String name, int healAmount) implements Consumable {
    @Override
    public void use(GameCharacter target) {
        target.heal(healAmount);
    }
}