package it.unicam.cs.mpgc.rpg130130.combat;

import it.unicam.cs.mpgc.rpg130130.model.Player;
import it.unicam.cs.mpgc.rpg130130.model.Enemy;

public class CombatSystem {
    public void fight(Player player, Enemy enemy) {

        System.out.println("Inizia il combattimento!");

        while (player.isAlive() && enemy.isAlive()) {
            // PLAYER ATTACCA
            enemy.takeDamage(player.getAttack());
            System.out.println("[ATTACCO] " + player.getName() + " attacca " + enemy.getName() +
                    " -> HP nemico: " + enemy.getHp());

            // NEMICO CONTRATTACCA
            if (enemy.isAlive()) {
                player.takeDamage(enemy.getAttack());
                System.out.println("[DANNO] " + enemy.getName() +
                        " contrattacca -> HP player: " + player.getHp());
            }
        }

        // RISULTATO FINALE
        if (player.isAlive()) {
            System.out.println(player.getName() + " ha vinto!");
        } else {
            System.out.println(enemy.getName() + " ha vinto!");
        }

        }
    }

