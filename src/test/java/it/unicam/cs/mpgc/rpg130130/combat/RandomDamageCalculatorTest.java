package it.unicam.cs.mpgc.rpg130130.combat;

import it.unicam.cs.mpgc.rpg130130.model.Enemy;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomDamageCalculatorTest {

    private final Enemy attacker = new Enemy("Goblin", 100, 10);

    @Test
    void damageStaysWithinExpectedRange() {
        RandomDamageCalculator calculator = new RandomDamageCalculator(new Random(42));

        for (int i = 0; i < 1000; i++) {
            Damage damage = calculator.calculate(attacker);
            if (damage.critical()) {
                assertTrue(damage.amount() >= 10 && damage.amount() <= 30);
            } else {
                assertTrue(damage.amount() >= 5 && damage.amount() <= 15);
            }
        }
    }

    @Test
    void sameSeedGivesSameDamage() {
        RandomDamageCalculator first = new RandomDamageCalculator(new Random(7));
        RandomDamageCalculator second = new RandomDamageCalculator(new Random(7));

        for (int i = 0; i < 20; i++) {
            assertEquals(first.calculate(attacker), second.calculate(attacker));
        }
    }
}
