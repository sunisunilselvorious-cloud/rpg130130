package it.unicam.cs.mpgc.rpg130130.persistence;

import it.unicam.cs.mpgc.rpg130130.model.Player;

public interface GameRepository {

    void save(Player player);

    Player load();
}