package uk.ungureanu.clickgame.config;

import uk.ungureanu.clickgame.entities.game.ClassicGameEntity;
import uk.ungureanu.clickgame.entities.game.FixedEntity;
import uk.ungureanu.clickgame.entities.game.GameEntity;
import uk.ungureanu.clickgame.entities.screen.ScreenCircle;
import uk.ungureanu.clickgame.gamelogic.ClassicGameMaster;

import java.awt.*;
import java.util.Random;

public class AvoidBuilder implements EntityBuilder {
    private final int screenWidth,screenHeight;
    private ClassicGameMaster gm;
    public AvoidBuilder(int screenWidth, int screenHeight, ClassicGameMaster gm) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gm = gm;
    }

    @Override
    public ClassicGameEntity createEntity() {
        double x,y,size;
        Random rand = new Random();
        x = rand.nextDouble() * screenWidth;
        y = rand.nextDouble() * screenHeight + 100;
        size = Math.max(3,rand.nextGaussian()*5 + 5);
        ScreenCircle childComponent = new ScreenCircle(x,y,5*size);
        FixedEntity newEntity = new FixedEntity(childComponent, false, gm);
        newEntity.getScreenEntity().setGameEntity(newEntity);
        newEntity.setScreenEntityColour(Color.RED);
        return newEntity;
    }

    @Override
    public ClassicGameEntity createEntity(double minSize, double maxSize) {
        double x,y,size;
        Random rand = new Random();
        x = rand.nextDouble() * screenWidth;
        y = rand.nextDouble() * screenHeight + 100;
        size = Math.min(maxSize,Math.max(minSize,rand.nextGaussian()*10 + 5));
        ScreenCircle childComponent = new ScreenCircle(x,y,5*size);
        FixedEntity newEntity = new FixedEntity(childComponent, false, gm);
        newEntity.getScreenEntity().setGameEntity(newEntity);
        newEntity.setScreenEntityColour(Color.RED);
        return newEntity;
    }
}
