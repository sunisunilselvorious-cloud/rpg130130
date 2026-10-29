package it.unicam.cs.mpgc.rpg130130.ui;

import it.unicam.cs.mpgc.rpg130130.GameConfig;
import it.unicam.cs.mpgc.rpg130130.combat.AttackReport;
import it.unicam.cs.mpgc.rpg130130.combat.BattleResult;
import it.unicam.cs.mpgc.rpg130130.combat.BattleState;
import it.unicam.cs.mpgc.rpg130130.game.GameSession;
import it.unicam.cs.mpgc.rpg130130.model.GameCharacter;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.UncheckedIOException;

public class GameUI extends Application {

    private final GameSession session = GameConfig.createSession();

    private final Label playerHpLabel = new Label();
    private final Label enemyHpLabel = new Label();
    private final ProgressBar playerHpBar = new ProgressBar(1.0);
    private final ProgressBar enemyHpBar = new ProgressBar(1.0);
    private final TextArea battleLog = new TextArea();
    private final Button fightButton = new Button("Combatti");
    private final Button saveButton = new Button("Salva");
    private final Button loadButton = new Button("Carica");
    private final Button newGameButton = new Button("New Game");

    @Override
    public void start(Stage stage) {
        battleLog.setEditable(false);
        battleLog.setPrefHeight(150);
        battleLog.setText("Benvenuto nell'arena!\n");

        fightButton.setOnAction(e -> onFight());
        saveButton.setOnAction(e -> onSave());
        loadButton.setOnAction(e -> onLoad());
        newGameButton.setOnAction(e -> onNewGame());

        HBox fighters = new HBox(80,
                fighterBox(session.getPlayer(), "images/player.png", playerHpLabel, playerHpBar),
                fighterBox(session.getEnemy(), "images/enemy.png", enemyHpLabel, enemyHpBar));
        fighters.setAlignment(Pos.CENTER);

        HBox buttons = new HBox(10, fightButton, saveButton, loadButton, newGameButton);
        buttons.setAlignment(Pos.CENTER);

        VBox center = new VBox(20, fighters, buttons, battleLog);
        center.setAlignment(Pos.CENTER);

        refresh();
        stage.setTitle("RPG Game");
        stage.setScene(new Scene(new BorderPane(center), 900, 600));
        stage.show();
    }

    private void onFight() {
        BattleResult result = session.fight();
        result.reports().stream()
                .map(this::describe)
                .forEach(battleLog::appendText);
        switch (result.state()) {
            case PLAYER_WON -> battleLog.appendText("Hai vinto!\n\n");
            case PLAYER_LOST -> battleLog.appendText("Hai perso!\n\n");
            case ONGOING -> battleLog.appendText("\n");
        }
        refresh();
    }

    private void onSave() {
        try {
            session.save();
            battleLog.appendText("Giocatore salvato!\n");
        } catch (UncheckedIOException ex) {
            battleLog.appendText("Errore nel salvataggio.\n");
        }
    }

    private void onLoad() {
        try {
            if (session.load()) {
                battleLog.setText("Caricato: " + session.getPlayer().getName() + "\n");
                refresh();
            } else {
                battleLog.appendText("Nessun salvataggio trovato\n");
            }
        } catch (UncheckedIOException ex) {
            battleLog.appendText("Errore nel caricamento.\n");
        }
    }

    private void onNewGame() {
        session.newGame();
        battleLog.setText("Nuova partita iniziata!\n");
        refresh();
    }

    private VBox fighterBox(GameCharacter character, String imagePath, Label hpLabel, ProgressBar bar) {
        ImageView image = new ImageView(new Image(imagePath));
        image.setFitWidth(250);
        image.setPreserveRatio(true);
        bar.setPrefWidth(200);

        VBox box = new VBox(10, new Label(character.getName().toUpperCase()), image, hpLabel, bar);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    private void refresh() {
        GameCharacter player = session.getPlayer();
        GameCharacter enemy = session.getEnemy();
        playerHpLabel.setText("HP Player: " + player.getHp());
        enemyHpLabel.setText("HP Nemico: " + enemy.getHp());
        playerHpBar.setProgress((double) player.getHp() / player.getMaxHp());
        enemyHpBar.setProgress((double) enemy.getHp() / enemy.getMaxHp());
        fightButton.setDisable(session.getState() != BattleState.ONGOING);
    }

    private String describe(AttackReport report) {
        return String.format("%s infligge %d danni a %s%s%n",
                report.attacker(), report.damage(), report.defender(),
                report.critical() ? " (CRITICO!)" : "");
    }

    public static void main(String[] args) {
        launch(args);
    }
}