package it.unicam.cs.mpgc.rpg130130.game;

import it.unicam.cs.mpgc.rpg130130.combat.BattleResult;
import it.unicam.cs.mpgc.rpg130130.combat.BattleState;
import it.unicam.cs.mpgc.rpg130130.combat.CombatAction;
import it.unicam.cs.mpgc.rpg130130.combat.CombatSystem;
import it.unicam.cs.mpgc.rpg130130.model.Enemy;
import it.unicam.cs.mpgc.rpg130130.model.Player;
import it.unicam.cs.mpgc.rpg130130.persistence.GameRepository;

import java.util.Optional;
import java.util.function.Supplier;

public class GameSession {
    private final Supplier<Player> playerFactory;
    private final Supplier<Enemy> enemyFactory;
    private final CombatSystem combatSystem;
    private final CombatAction playerAction;
    private final GameRepository repository;

    private Player player;
    private Enemy enemy;
    private BattleState state;

    public GameSession(Supplier<Player> playerFactory,
                       Supplier<Enemy> enemyFactory,
                       CombatSystem combatSystem,
                       CombatAction playerAction,
                       GameRepository repository) {
        this.playerFactory = playerFactory;
        this.enemyFactory = enemyFactory;
        this.combatSystem = combatSystem;
        this.playerAction = playerAction;
        this.repository = repository;
        newGame();
    }

    public final void newGame() {
        player = playerFactory.get();
        enemy = enemyFactory.get();
        state = BattleState.ONGOING;
    }

    public BattleResult fight() {
        if (state != BattleState.ONGOING) {
            throw new IllegalStateException("La battaglia è già conclusa");
        }
        BattleResult result = combatSystem.playTurn(player, enemy, playerAction);
        state = result.state();
        return result;
    }

    public void save() {
        repository.save(player);
    }

    /** @return true se è stato trovato un salvataggio. Il nemico riparte da zero. */
    public boolean load() {
        Optional<Player> loaded = Optional.ofNullable(repository.load());
        loaded.ifPresent(p -> {
            player = p;
            enemy = enemyFactory.get();
            state = combatSystem.stateOf(player, enemy);
        });
        return loaded.isPresent();
    }

    public Player getPlayer()     { return player; }
    public Enemy getEnemy()       { return enemy; }
    public BattleState getState() { return state; }
}
