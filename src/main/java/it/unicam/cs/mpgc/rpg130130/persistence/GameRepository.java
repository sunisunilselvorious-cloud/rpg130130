package it.unicam.cs.mpgc.rpg130130.persistence;

import it.unicam.cs.mpgc.rpg130130.model.Player;

import java.util.Optional;

public interface GameRepository {
    void save(Player player);
    Optional<Player> load();
}