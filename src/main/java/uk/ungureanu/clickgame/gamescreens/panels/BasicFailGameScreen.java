package uk.ungureanu.clickgame.gamescreens.panels;

import uk.ungureanu.clickgame.config.Configurator;
import uk.ungureanu.clickgame.entities.game.ClassicGameEntity;
import uk.ungureanu.clickgame.entities.screen.ScreenEntity;
import uk.ungureanu.clickgame.gamelogic.ClassicGameMaster;
import uk.ungureanu.clickgame.gamelogic.GameLoop;
import uk.ungureanu.clickgame.gamescreens.MainScreen;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BasicFailGameScreen extends FailGameScreen {

    public BasicFailGameScreen() {
        this.setLayout(null);
        this.setBackground(Color.RED);
        JLabel failLabel = new JLabel("GAME OVER");
        failLabel.setForeground(Color.ORANGE);
        failLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
        failLabel.setBounds(MainScreen.getInstance().getWidth()/2-120, 10, 200, 100);
        JButton restartButton = new JButton("TRY AGAIN?");
        restartButton.setBounds(MainScreen.getInstance().getWidth()/2-100, 250, 150, 40);
        this.add(failLabel);
        this.add(restartButton);
        restartButton.addActionListener((e) -> {
            this.setVisible(false);
            this.restartGame();
        });
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
