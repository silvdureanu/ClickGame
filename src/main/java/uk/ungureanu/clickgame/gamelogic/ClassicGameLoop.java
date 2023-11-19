package uk.ungureanu.clickgame.gamelogic;

import uk.ungureanu.clickgame.entities.game.ClassicGameEntity;

import uk.ungureanu.clickgame.gamescreens.panels.GameplayScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ListIterator;

public class ClassicGameLoop implements GameLoop {
    private List<ClassicGameEntity> gameEntities;
    private ClassicGameMaster gameMaster;
    private int runtime = 0;
    private GameplayScreen gameplayScreen;
    public ClassicGameLoop(List<ClassicGameEntity> entities, ClassicGameMaster gm, GameplayScreen gameplayScreen) {
        this.gameEntities = entities;
        this.gameMaster = gm;
        this.gameplayScreen = gameplayScreen;
    }

    @Override
    public void checkState() {
        gameMaster.checkState(gameEntities);
    }


    @Override
    public void loop() {
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                runtime+=10;
                checkState();
            }
        });
        timer.start();
    }

    public int getTimer() {
        return this.runtime;
    }

    @Override
    public void removeEntity(ClassicGameEntity entity) {
        ListIterator<ClassicGameEntity> iterator = this.gameEntities.listIterator();
        while(iterator.hasNext()) {
            ClassicGameEntity nextEntity = iterator.next();
            if (nextEntity.equals(entity)) {
                iterator.remove();
                break;
            }
        }
        this.gameplayScreen.removeElement(entity.getScreenEntity());
    }

    public void end() {
        this.gameplayScreen.setVisible(false);
    }
}
