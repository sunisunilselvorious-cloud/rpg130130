package it.unicam.cs.mpgc.rpg130130.ui;

import it.unicam.cs.mpgc.rpg130130.combat.CombatSystem;
import it.unicam.cs.mpgc.rpg130130.model.Enemy;
import it.unicam.cs.mpgc.rpg130130.model.Player;
import it.unicam.cs.mpgc.rpg130130.persistence.GameRepository;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import it.unicam.cs.mpgc.rpg130130.persistence.FileGameRepository;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import java.util.Random;
import javafx.scene.control.ProgressBar;

public class GameUI extends Application {

    @Override
    public void start(Stage stage) {

        ImageView playerImg = new ImageView(new Image("images/player.png"));
        ImageView enemyImg = new ImageView(new Image("images/enemy.png"));

        playerImg.setFitWidth(250);
        playerImg.setPreserveRatio(true);

        enemyImg.setFitWidth(250);
        enemyImg.setPreserveRatio(true);

        TextArea battleLog = new TextArea();
        battleLog.setEditable(false);
        battleLog.setPrefHeight(150);
        battleLog.setText("Benvenuto nell'arena!\n");


        Label playerName = new Label("HERO");
        Label enemyName = new Label("GOBLIN");


        Button fightButton = new Button(" Combatti ");
        Button saveButton = new Button("Salva");
        Button loadButton = new Button("Carica");
        Button newGameButton = new Button("New Game");

        GameRepository repo = new FileGameRepository();
        Random random = new Random();

        Player[] playerHolder = new Player[1];
        playerHolder[0] = new Player("Hero", 100, 10);

        Enemy[] enemyHolder = new Enemy[1];
        enemyHolder[0] = new Enemy("Goblin", 120, 25);

        Label playerHpLabel = new Label(
                "HP Player: " + playerHolder[0].getHp()
        );

        Label enemyHpLabel = new Label(
                "HP Nemico: " + enemyHolder[0].getHp()
        );

        ProgressBar playerHpBar = new ProgressBar(1.0);
        ProgressBar enemyHpBar = new ProgressBar(1.0);

        playerHpBar.setPrefWidth(200);
        enemyHpBar.setPrefWidth(200);


        fightButton.setOnAction(e -> {
            Player player = playerHolder[0];
            Enemy enemy = enemyHolder[0];

            int playerDamage = random.nextInt(11) + 5; // da 5 a 15
            if (random.nextInt(100) < 15) {
                playerDamage *= 2;
            }
            int enemyDamage = random.nextInt(11) + 5;  // da 5 a 15
            if (random.nextInt(100) < 15) {
                enemyDamage *= 2;
            }


            enemy.takeDamage(playerDamage);

            String message =
                    "Hero infligge " + playerDamage + " danni al Goblin.\n";

            if (enemy.isAlive()) {
                player.takeDamage(enemyDamage);

                message +=
                        "Goblin infligge " + enemyDamage + " danni a Hero.";
            }

            playerHpLabel.setText("HP Player: " + player.getHp());
            enemyHpLabel.setText("HP Nemico: " + enemy.getHp());
            playerHpBar.setProgress(player.getHp() / 100.0);
            enemyHpBar.setProgress(enemy.getHp() / 120.0);

            if (!enemy.isAlive()) {
                battleLog.appendText(message + "\nHai vinto!\n\n");
                fightButton.setDisable(true);
            }
            else if (!player.isAlive()) {
                battleLog.appendText(message + "\nHai perso!\n\n");
                fightButton.setDisable(true);
            }
            else {
                battleLog.appendText(message + "\n\n");
            }
        });

        saveButton.setOnAction(e -> {
            repo.save(playerHolder[0]);
            battleLog.appendText("Giocatore salvato! \n");
        });

        loadButton.setOnAction(e -> {
            Player loaded = repo.load();

            if (loaded != null) {
                playerHolder[0] = loaded;


                enemyHolder[0] = new Enemy("Goblin", 120, 25);


                playerHpLabel.setText("HP Player: " + playerHolder[0].getHp());
                enemyHpLabel.setText("HP Nemico: " + enemyHolder[0].getHp());
                playerHpBar.setProgress(playerHolder[0].getHp() / 100.0);
                enemyHpBar.setProgress(1.0);

                battleLog.setText("Caricato: " + playerHolder[0].getName() + "\n");

                fightButton.setDisable(false);
            } else {
                battleLog.appendText("Nessun salvataggio trovato\n");
            }
        });

        newGameButton.setOnAction(e -> {

            playerHolder[0] = new Player("Hero", 100, 10);
            enemyHolder[0] = new Enemy("Goblin", 120, 25);

            playerHpLabel.setText("HP Player: " + playerHolder[0].getHp());
            enemyHpLabel.setText("HP Nemico: " + enemyHolder[0].getHp());
            playerHpBar.setProgress(1.0);
            enemyHpBar.setProgress(1.0);

            battleLog.setText("Nuova partita iniziata!\n");

            fightButton.setDisable(false);
        });

        HBox fighters = new HBox(80);

        VBox playerBox = new VBox(10);
        playerBox.setAlignment(Pos.CENTER);
        playerBox.getChildren().addAll(
                playerName,
                playerImg,
                playerHpLabel,
                playerHpBar
        );

        VBox enemyBox = new VBox(10);
        enemyBox.setAlignment(Pos.CENTER);
        enemyBox.getChildren().addAll(
                enemyName,
                enemyImg,
                enemyHpLabel,
                enemyHpBar
        );

        fighters.setAlignment(Pos.CENTER);
        fighters.getChildren().addAll(
                playerBox,
                enemyBox
        );

        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(
                fightButton,
                saveButton,
                loadButton,
                newGameButton
        );

        VBox center = new VBox(20);
        center.setAlignment(Pos.CENTER);
        center.getChildren().addAll(
                fighters,
                buttons,
                battleLog
        );

        BorderPane root = new BorderPane();
        root.setCenter(center);

        Scene scene = new Scene(root, 900, 600);


        stage.setTitle("RPG Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}