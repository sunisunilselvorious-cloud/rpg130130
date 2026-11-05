package it.unicam.cs.mpgc.rpg130130.model;

import it.unicam.cs.mpgc.rpg130130.item.Potion;
import it.unicam.cs.mpgc.rpg130130.item.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {

    @Test
    void equipmentIncreasesAttack() {
        Player player = new Player("Hero", 100, 10);
        player.addItem(new Weapon("Spada", 7));

        assertEquals(17, player.getAttack());
    }

    @Test
    void potionHealsAndIsConsumed() {
        Player player = new Player("Hero", 100, 40, 10);
        Potion potion = new Potion("Pozione", 30);
        player.addItem(potion);

        assertTrue(player.use(potion));
        assertEquals(70, player.getHp());
        assertFalse(player.use(potion));
    }

    @Test
    void healingNeverExceedsMaxHp() {
        Player player = new Player("Hero", 100, 90, 10);
        Potion potion = new Potion("Pozione", 30);
        player.addItem(potion);

        player.use(potion);

        assertEquals(100, player.getHp());
    }

    @Test
    void usingAnItemNotInInventoryDoesNothing() {
        Player player = new Player("Hero", 100, 40, 10);

        assertFalse(player.use(new Potion("Pozione", 30)));
        assertEquals(40, player.getHp());
    }
}
