package it.unicam.cs.mpgc.rpg130130.item;

import it.unicam.cs.mpgc.rpg130130.model.GameCharacter;

public interface Consumable extends Item {
    void use(GameCharacter target);
}