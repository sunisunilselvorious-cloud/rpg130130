package it.unicam.cs.mpgc.rpg130130;

import it.unicam.cs.mpgc.rpg130130.model.Player;
import it.unicam.cs.mpgc.rpg130130.model.Enemy;
import it.unicam.cs.mpgc.rpg130130.combat.CombatSystem;
import it.unicam.cs.mpgc.rpg130130.persistence.FileGameRepository;

public class Main {
    public static void main(String[] args) {
        FileGameRepository repo = new FileGameRepository();

        Player player;


        player = repo.load();

        if (player == null) {
            System.out.println("Nuovo giocatore creato!");
            player = new Player("Hero", 100, 10);
        } else {
            System.out.println("Bentornato " + player.getName());
        }

        Enemy enemy = new Enemy("Goblin", 50, 5);

        CombatSystem combat = new CombatSystem();
        combat.fight(player, enemy);


        repo.save(player);
    }
}
