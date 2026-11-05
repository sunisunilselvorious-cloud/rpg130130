package it.unicam.cs.mpgc.rpg130130.game;

import it.unicam.cs.mpgc.rpg130130.combat.BasicAttack;
import it.unicam.cs.mpgc.rpg130130.combat.BattleState;
import it.unicam.cs.mpgc.rpg130130.combat.CombatAction;
import it.unicam.cs.mpgc.rpg130130.combat.CombatSystem;
import it.unicam.cs.mpgc.rpg130130.combat.Damage;
import it.unicam.cs.mpgc.rpg130130.combat.DamageCalculator;
import it.unicam.cs.mpgc.rpg130130.model.Enemy;
import it.unicam.cs.mpgc.rpg130130.model.Player;
import it.unicam.cs.mpgc.rpg130130.persistence.GameRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameSessionTest {

    private static class InMemoryRepository implements GameRepository {
        private Player saved;

        @Override
        public void save(Player player) {
            saved = player;
        }

        @Override
        public Optional<Player> load() {
            return Optional.ofNullable(saved);
        }
    }

    private GameSession sessionWithEnemyHp(int enemyHp) {
        DamageCalculator fixed = a -> new Damage(a.getAttack(), false);
        CombatAction attack = new BasicAttack(fixed);
        return new GameSession(
                () -> new Player("Hero", 100, 10),
                () -> new Enemy("Goblin", enemyHp, 5),
                new CombatSystem(attack),
                attack,
                new InMemoryRepository());
    }

    @Test
    void newSessionStartsOngoing() {
        assertEquals(BattleState.ONGOING, sessionWithEnemyHp(50).getState());
    }

    @Test
    void cannotFightAfterBattleIsOver() {
        GameSession session = sessionWithEnemyHp(10);

        session.fight();

        assertEquals(BattleState.PLAYER_WON, session.getState());
        assertThrows(IllegalStateException.class, session::fight);
    }

    @Test
    void newGameResetsTheBattle() {
        GameSession session = sessionWithEnemyHp(10);
        session.fight();

        session.newGame();

        assertEquals(BattleState.ONGOING, session.getState());
        assertEquals(10, session.getEnemy().getHp());
        assertEquals(100, session.getPlayer().getHp());
    }

    @Test
    void saveAndLoadRestoreThePlayer() {
        GameSession session = sessionWithEnemyHp(50);
        session.fight();
        int hpAfterFight = session.getPlayer().getHp();
        session.save();

        session.newGame();
        assertEquals(100, session.getPlayer().getHp());

        assertTrue(session.load());
        assertEquals(hpAfterFight, session.getPlayer().getHp());
        assertEquals(50, session.getEnemy().getHp());
    }

    @Test
    void loadWithoutSaveReturnsFalse() {
        assertFalse(sessionWithEnemyHp(50).load());
    }
}
