package it.unicam.cs.mpgc.rpg130130;

import it.unicam.cs.mpgc.rpg130130.combat.BasicAttack;
import it.unicam.cs.mpgc.rpg130130.combat.CombatAction;
import it.unicam.cs.mpgc.rpg130130.combat.CombatSystem;
import it.unicam.cs.mpgc.rpg130130.combat.DamageCalculator;
import it.unicam.cs.mpgc.rpg130130.combat.RandomDamageCalculator;
import it.unicam.cs.mpgc.rpg130130.game.GameSession;
import it.unicam.cs.mpgc.rpg130130.model.Enemy;
import it.unicam.cs.mpgc.rpg130130.model.Player;
import it.unicam.cs.mpgc.rpg130130.persistence.FileGameRepository;

import java.util.Random;

public final class GameConfig {
    private GameConfig() { }

    public static GameSession createSession() {
        DamageCalculator calculator = new RandomDamageCalculator(new Random());
        CombatAction attack = new BasicAttack(calculator);
        return new GameSession(
                () -> new Player("Hero", 100, 10),
                () -> new Enemy("Goblin", 120, 8),
                new CombatSystem(attack),
                attack,
                new FileGameRepository());
    }
}