package it.unicam.cs.mpgc.rpg130130.persistence;

import it.unicam.cs.mpgc.rpg130130.model.Player;

public record PlayerSnapshot(String name, int maxHp, int hp, int attack) {

    public static PlayerSnapshot from(Player player) {
        return new PlayerSnapshot(player.getName(), player.getMaxHp(),
                player.getHp(), player.getBaseAttack());
    }

    public Player toPlayer() {
        return new Player(name, maxHp, hp, attack);
    }
}
