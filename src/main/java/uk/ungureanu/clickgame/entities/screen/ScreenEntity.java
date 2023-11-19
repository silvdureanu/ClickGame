package uk.ungureanu.clickgame.entities.screen;

import uk.ungureanu.clickgame.entities.game.GameEntity;

import java.awt.*;

public interface ScreenEntity extends Shape {
    //Decouples entities in the game logic from shapes shown on screen. Passes on click events to GameEntity

    GameEntity getGameEntity();
    //Callback to enclosing Game Entity

    void setGameEntity(GameEntity parent);

    void onClick();

    void setColour(Color newColour);

    Color getColour();

}
