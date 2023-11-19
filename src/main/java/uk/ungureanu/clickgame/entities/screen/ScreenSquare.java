package uk.ungureanu.clickgame.entities.screen;

import uk.ungureanu.clickgame.entities.game.GameEntity;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ScreenSquare extends Rectangle2D.Double implements ScreenEntity {
    private GameEntity parent;
    private Color colour;

    public ScreenSquare(double x, double y, double w) {
        super(x,y,w,w);
    }

    @Override
    public GameEntity getGameEntity() {
        return this.parent;
    }

    public void setGameEntity(GameEntity parent) {
        this.parent = parent;
    }

    public void onClick() {
        this.parent.onClick();
    }

    public void setColour(Color newColour) {
        this.colour = newColour;
    }

    public Color getColour() {
        return this.colour;
    }

}
