package uk.ungureanu.clickgame.gamescreens.panels;

import uk.ungureanu.clickgame.config.Configurator;
import uk.ungureanu.clickgame.entities.game.ClassicGameEntity;
import uk.ungureanu.clickgame.entities.game.GameEntity;
import uk.ungureanu.clickgame.entities.screen.ScreenEntity;
import uk.ungureanu.clickgame.gamelogic.ClassicGameMaster;
import uk.ungureanu.clickgame.gamelogic.GameLoop;
import uk.ungureanu.clickgame.gamescreens.MainScreen;

import javax.swing.*;
import java.util.List;

public class BasicNewGameScreen extends NewGameScreen  {

    public BasicNewGameScreen() {
        this.setLayout(null);
        JButton newGameButton = new JButton("NEW GAME");
        newGameButton.setBounds(MainScreen.getInstance().getWidth()/2-100, 250, 150, 40);
        add(newGameButton);
        newGameButton.addActionListener((e) -> {
            this.setVisible(false);
            this.startGame();
        });
    }

    @Override
    public void startGame() {
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
