package uk.ungureanu.clickgame.config;

import uk.ungureanu.clickgame.entities.game.ChangingEntity;
import uk.ungureanu.clickgame.entities.game.ClassicGameEntity;
import uk.ungureanu.clickgame.entities.game.GameEntity;
import uk.ungureanu.clickgame.entities.screen.ScreenSquare;
import uk.ungureanu.clickgame.gamelogic.ClassicGameMaster;

import java.awt.*;
import java.util.Random;

public class ChangeBuilder implements EntityBuilder {
    private final int screenWidth,screenHeight;
    private ClassicGameMaster gm;
    public ChangeBuilder(int screenWidth, int screenHeight, ClassicGameMaster gm) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gm = gm;
    }

    @Override
    public ClassicGameEntity createEntity() {
        double x,y,size;
        boolean startingState;
        int changeInterval;
        Random rand = new Random();
        x = rand.nextDouble() * screenWidth;
        y = rand.nextDouble() * screenHeight + 100;
        size = Math.max(3,rand.nextGaussian()*5 + 5);
        changeInterval = Math.max(500,(int)(rand.nextGaussian()*2000+4000));
        startingState = rand.nextBoolean();
        ScreenSquare childComponent = new ScreenSquare(x,y,5*size);
        ChangingEntity newEntity = new ChangingEntity(childComponent, startingState, gm, changeInterval);
        newEntity.getScreenEntity().setGameEntity(newEntity);
        newEntity.setScreenEntityColour(startingState ? Color.GREEN : Color.RED);
        return newEntity;
    }

    @Override
    public ClassicGameEntity createEntity(double minSize, double maxSize) {
        double x,y,size;
        boolean startingState;
        int changeInterval;
        Random rand = new Random();
        x = rand.nextDouble() * screenWidth;
        y = rand.nextDouble() * screenHeight + 100;
        size = Math.min(maxSize,Math.max(minSize,rand.nextGaussian()*10 + 5));
        changeInterval = Math.max(500,(int)(rand.nextGaussian()*2000+4000));
        startingState = rand.nextBoolean();
        ScreenSquare childComponent = new ScreenSquare(x,y,5*size);
        ChangingEntity newEntity = new ChangingEntity(childComponent, startingState, gm, changeInterval);
        newEntity.getScreenEntity().setGameEntity(newEntity);
        newEntity.setScreenEntityColour(startingState ? Color.GREEN : Color.RED);
        return newEntity;
    }
}
