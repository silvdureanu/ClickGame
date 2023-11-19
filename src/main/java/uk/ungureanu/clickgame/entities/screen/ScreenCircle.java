package uk.ungureanu.clickgame.entities.screen;

import uk.ungureanu.clickgame.entities.game.GameEntity;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class ScreenCircle extends Ellipse2D.Double implements ScreenEntity {
    private GameEntity parent;
    private Color colour;


    public ScreenCircle(double x, double y, double w) {
        super(x,y,w,w);
    }

    @Override
    public GameEntity getGameEntity() {
        return this.parent;
    }

    public void setGameEntity(GameEntity parent) {
        this.parent = parent;
    }

    @Override
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
