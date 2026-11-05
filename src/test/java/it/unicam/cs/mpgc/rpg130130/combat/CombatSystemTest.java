package it.unicam.cs.mpgc.rpg130130.combat;

import it.unicam.cs.mpgc.rpg130130.model.Enemy;
import it.unicam.cs.mpgc.rpg130130.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CombatSystemTest {

    private static final DamageCalculator FIXED = a -> new Damage(a.getAttack(), false);
    private final CombatAction attack = new BasicAttack(FIXED);
    private final CombatSystem combat = new CombatSystem(attack);

    @Test
    void enemyCounterattacksWhenStillAlive() {
        Player player = new Player("Hero", 100, 10);
        Enemy enemy = new Enemy("Goblin", 50, 5);

        BattleResult result = combat.playTurn(player, enemy, attack);

        assertEquals(2, result.reports().size());
        assertEquals(40, enemy.getHp());
        assertEquals(95, player.getHp());
        assertEquals(BattleState.ONGOING, result.state());
    }

    @Test
    void deadEnemyDoesNotCounterattack() {
        Player player = new Player("Hero", 100, 10);
        Enemy enemy = new Enemy("Goblin", 10, 5);

        BattleResult result = combat.playTurn(player, enemy, attack);

        assertEquals(1, result.reports().size());
        assertEquals(100, player.getHp());
        assertEquals(BattleState.PLAYER_WON, result.state());
    }

    @Test
    void playerLosesWhenHpReachesZero() {
        Player player = new Player("Hero", 5, 10);
        Enemy enemy = new Enemy("Goblin", 50, 5);

        BattleResult result = combat.playTurn(player, enemy, attack);

        assertEquals(0, player.getHp());
        assertEquals(BattleState.PLAYER_LOST, result.state());
    }
}