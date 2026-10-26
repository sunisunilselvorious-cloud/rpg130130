package it.unicam.cs.mpgc.rpg130130.persistence;

import com.google.gson.Gson;
import it.unicam.cs.mpgc.rpg130130.model.Player;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FileGameRepository implements GameRepository {
    private final Path file;
    private final Gson gson = new Gson();

    public FileGameRepository() {
        this(Path.of("save.json"));
    }

    public FileGameRepository(Path file) {
        this.file = file;
    }

    @Override
    public void save(Player player) {
        try (Writer writer = Files.newBufferedWriter(file)) {
            gson.toJson(PlayerSnapshot.from(player), writer);
        } catch (IOException e) {
            throw new UncheckedIOException("Impossibile salvare la partita", e);
        }
    }

    @Override
    public Optional<Player> load() {
        if (!Files.exists(file)) {
            return Optional.empty();
        }
        try (Reader reader = Files.newBufferedReader(file)) {
            return Optional.ofNullable(gson.fromJson(reader, PlayerSnapshot.class))
                    .map(PlayerSnapshot::toPlayer);
        } catch (IOException e) {
            throw new UncheckedIOException("Impossibile caricare la partita", e);
        }
    }
}