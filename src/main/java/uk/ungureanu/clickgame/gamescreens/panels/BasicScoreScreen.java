package uk.ungureanu.clickgame.gamescreens.panels;

import uk.ungureanu.clickgame.config.Configurator;
import uk.ungureanu.clickgame.entities.game.ClassicGameEntity;
import uk.ungureanu.clickgame.entities.screen.ScreenEntity;
import uk.ungureanu.clickgame.gamelogic.ClassicGameMaster;
import uk.ungureanu.clickgame.gamelogic.GameLoop;
import uk.ungureanu.clickgame.gamescreens.MainScreen;
import uk.ungureanu.clickgame.leaderboard.PlayerRecord;
import uk.ungureanu.clickgame.leaderboard.Ranking;
import uk.ungureanu.clickgame.storage.RankingStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

public class BasicScoreScreen extends ScoreScreen {
    private int finishTime;
    private boolean focusedNameField = false;
    public BasicScoreScreen(int finishTime) {
        Configurator config = MainScreen.getInstance().getConfig();
        this.setLayout(null);
        this.finishTime = finishTime;
        this.createScoreLabel();
        this.createRestartButton();
        RankingStorage rankingStorage = config.createRankingStorage();
        //Retrieve ranking from storage; if none exists, create a new one.
        Ranking storedRanking = rankingStorage.getRanking().orElse(config.createRanking());
        List<PlayerRecord> topRankings = storedRanking.getTopPlayers(3);
        JLabel rankingLabel = new JLabel(this.generateRankingTable(topRankings));
        rankingLabel.setBounds(MainScreen.getInstance().getWidth()/2-100, 100, 200, 100);
        this.add(rankingLabel);

        JTextField usernameField = new JTextField("Input your name");
        usernameField.setBounds(MainScreen.getInstance().getWidth()/2-100, 400, 200, 20);
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                usernameField.setText("");
                focusedNameField = true;
            }
            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        JButton submitName = new JButton("Submit");
        submitName.setBounds(MainScreen.getInstance().getWidth()/2-100, 450, 150, 30);
        this.add(usernameField);
        this.add(submitName);

        submitName.addActionListener((e) -> {
            if (focusedNameField) {
                updateRankings(storedRanking,config, usernameField,submitName,rankingLabel);
            }
        });
        usernameField.addActionListener((e) -> {
            updateRankings(storedRanking,config, usernameField, submitName, rankingLabel);
        });

    }

    private void createRestartButton() {
        JButton restartButton = new JButton("Play again");
        restartButton.setBounds(MainScreen.getInstance().getWidth()/2-100, 800, 150, 30);
        this.add(restartButton);
        restartButton.addActionListener((e) -> {
            this.setVisible(false);
            this.restartGame();
        });
    }

    private void createScoreLabel() {
        JLabel scoreLabel = new JLabel("Final score: " + finishTime / 1000.0 +"s");
        scoreLabel.setForeground(Color.ORANGE);
        scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
        scoreLabel.setBounds(MainScreen.getInstance().getWidth()/2-120, 10, 500, 100);
        this.add(scoreLabel);
    }
    private void updateRankings(Ranking newRanking, Configurator config,
                                JTextField usernameField,JButton submitName, JLabel oldRankingLabel) {
        remove(usernameField);
        remove(submitName);
        remove(oldRankingLabel);
        newRanking.insertRecord(config.createPlayerRecord(usernameField.getText(), finishTime/1000.0));
        List<PlayerRecord> topRankings = newRanking.getTopPlayers(3);
        JLabel rankingLabel = new JLabel(this.generateRankingTable(topRankings));
        rankingLabel.setBounds(MainScreen.getInstance().getWidth()/2-100, 100, 200, 100);
        this.add(rankingLabel);
        config.createRankingStorage().storeRanking(newRanking);
    }

    private String generateRankingTable(List<PlayerRecord> rankings) {
        String table;
        if(rankings.size()>0) {
            table = "<html>Top scores:<br/>";
            for (PlayerRecord record : rankings) {
                table += record.getName();
                table += ":       ";
                table += Double.toString(record.getScore());
                table += "s<br/";
            }
        }
        else {
            table = "You are the first player!";
        }
        return table;
    }

    @Override
    public void restartGame() {
        Configurator config = MainScreen.getInstance().getConfig();
        ClassicGameMaster gm = config.createGameMaster();
        List<ClassicGameEntity> entities = config.createTiler(MainScreen.getInstance().getWidth(),MainScreen.getInstance().getHeight(),gm).generateEntities();
        List<ScreenEntity> screenEntities = ClassicGameEntity.getScreenEntityList(entities);
        GameplayScreen gameplayScreen = config.createGameplayScreen(screenEntities);
        MainScreen.getInstance().setPanel(gameplayScreen);
        GameLoop loop = config.createGameLoop(gm, entities,gameplayScreen);
        loop.loop();
    }

}
