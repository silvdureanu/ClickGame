package uk.ungureanu.clickgame.entities.game;

import uk.ungureanu.clickgame.entities.screen.ScreenEntity;
import uk.ungureanu.clickgame.gamelogic.ClassicGameMaster;

import java.awt.*;

public class FixedEntity implements ClassicGameEntity {
    private ScreenEntity shape;
    private final boolean clickable;
    private ClassicGameMaster gameMaster;

    public FixedEntity(ScreenEntity screenEntity, boolean clickable, ClassicGameMaster gm) {
        this.shape = screenEntity;
        this.clickable = clickable;
        this.gameMaster = gm;
    }

    @Override
    public ScreenEntity getScreenEntity() {
        return this.shape;
    }

    @Override
    public boolean isClickable() {
        return this.clickable;
    }

    @Override
    public void setScreenEntityColour(Color newColour) {
        this.shape.setColour(newColour);
    }

    @Override
    public void onClick() {
        this.gameMaster.clickCheck(this);
    }

    public void checkByGameMaster() {
        this.gameMaster.checkGameEntity(this);
    }

}


