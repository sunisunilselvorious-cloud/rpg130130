package it.unicam.cs.mpgc.rpg130130.combat;

import it.unicam.cs.mpgc.rpg130130.model.GameCharacter;

import java.util.Random;

public class RandomDamageCalculator implements DamageCalculator {
    private static final int VARIATION = 5;
    private static final int CRITICAL_CHANCE_PERCENT = 15;

    private final Random random;

    public RandomDamageCalculator(Random random) {
        this.random = random;
    }

    @Override
    public Damage calculate(GameCharacter attacker) {
        int base = attacker.getAttack() + random.nextInt(2 * VARIATION + 1) - VARIATION;
        boolean critical = random.nextInt(100) < CRITICAL_CHANCE_PERCENT;
        int amount = Math.max(1, critical ? base * 2 : base);
        return new Damage(amount, critical);
    }
}
