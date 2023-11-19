package uk.ungureanu.clickgame.entities.game;

import uk.ungureanu.clickgame.entities.screen.ScreenEntity;
import uk.ungureanu.clickgame.gamelogic.ClassicGameMaster;

import java.awt.*;

public class ChangingEntity implements ClassicGameEntity {
    private ScreenEntity shape;
    private boolean clickable;
    private ClassicGameMaster gameMaster;
    private int switchInterval;
    private boolean startingState;

    public ChangingEntity(ScreenEntity screenEntity, boolean initialState, ClassicGameMaster gm, int interval) {
        this.shape = screenEntity;
        this.clickable = initialState;
        this.gameMaster = gm;
        this.switchInterval = interval;
        this.startingState = initialState;
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
    public void checkByGameMaster() {
        this.gameMaster.checkGameEntity(this);
    }

    @Override
    public void onClick() {
        this.gameMaster.clickCheck(this);
    }

    public void setClickable(boolean newClickability) {
        this.clickable = newClickability;
    }

    public int getSwitchInterval() {
        return this.switchInterval;
    }

    public boolean getStartingState() {
        return this.startingState;
    }

}


