package it.unicam.cs.mpgc.rpg130130.persistence;

import it.unicam.cs.mpgc.rpg130130.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileGameRepositoryTest {

    @TempDir
    Path tempDir;

    @Test
    void loadReturnsEmptyWhenFileIsMissing() {
        FileGameRepository repository = new FileGameRepository(tempDir.resolve("save.json"));

        assertTrue(repository.load().isEmpty());
    }

    @Test
    void savedPlayerCanBeLoadedBack() {
        FileGameRepository repository = new FileGameRepository(tempDir.resolve("save.json"));
        repository.save(new Player("Hero", 100, 60, 10));

        Player loaded = repository.load().orElseThrow();

        assertEquals("Hero", loaded.getName());
        assertEquals(100, loaded.getMaxHp());
        assertEquals(60, loaded.getHp());
        assertEquals(10, loaded.getBaseAttack());
    }

    @Test
    void oldFormatFileIsIgnored() throws IOException {
        Path file = tempDir.resolve("save.json");
        Files.writeString(file, "{\"name\":\"Hero\",\"hp\":100,\"attack\":10}");

        assertTrue(new FileGameRepository(file).load().isEmpty());
    }
}