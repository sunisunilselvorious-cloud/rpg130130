package it.unicam.cs.mpgc.rpg130130.persistence;

import com.google.gson.Gson;
import it.unicam.cs.mpgc.rpg130130.model.Player;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileGameRepository implements GameRepository {

    private static final String FILE_NAME = "save.json";
    private Gson gson = new Gson();

    @Override
    public void save(Player player) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(player, writer);
            System.out.println("Gioco salvato in JSON!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Player load() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Player player = gson.fromJson(reader, Player.class);
            System.out.println("Gioco caricato!");
            return player;
        } catch (IOException e) {
            System.out.println("Nessun salvataggio trovato!");
            return null;
        }
    }
}