package it.unicam.cs.mpgc.rpg130130.combat;

import it.unicam.cs.mpgc.rpg130130.model.GameCharacter;

public class BasicAttack implements CombatAction {
    private final DamageCalculator calculator;

    public BasicAttack(DamageCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public AttackReport execute(GameCharacter actor, GameCharacter target) {
        Damage damage = calculator.calculate(actor);
        target.takeDamage(damage.amount());
        return new AttackReport(actor.getName(), target.getName(), damage.amount(), damage.critical());
    }
}