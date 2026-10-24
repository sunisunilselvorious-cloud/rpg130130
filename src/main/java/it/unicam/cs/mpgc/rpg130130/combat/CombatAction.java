package it.unicam.cs.mpgc.rpg130130.combat;

import it.unicam.cs.mpgc.rpg130130.model.GameCharacter;

public interface CombatAction {
    AttackReport execute(GameCharacter actor, GameCharacter target);
}
