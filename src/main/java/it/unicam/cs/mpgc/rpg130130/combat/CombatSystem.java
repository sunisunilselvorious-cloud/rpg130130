package it.unicam.cs.mpgc.rpg130130.combat;

import it.unicam.cs.mpgc.rpg130130.model.Enemy;
import it.unicam.cs.mpgc.rpg130130.model.Player;

import java.util.ArrayList;
import java.util.List;

public class CombatSystem {
    private final CombatAction enemyAction;

    public CombatSystem(CombatAction enemyAction) {
        this.enemyAction = enemyAction;
    }

    public BattleResult playTurn(Player player, Enemy enemy, CombatAction playerAction) {
        List<AttackReport> reports = new ArrayList<>();
        reports.add(playerAction.execute(player, enemy));
        if (enemy.isAlive()) {
            reports.add(enemyAction.execute(enemy, player));
        }
        return new BattleResult(List.copyOf(reports), stateOf(player, enemy));
    }

    public BattleState stateOf(Player player, Enemy enemy) {
        if (!enemy.isAlive()) return BattleState.PLAYER_WON;
        if (!player.isAlive()) return BattleState.PLAYER_LOST;
        return BattleState.ONGOING;
    }
}