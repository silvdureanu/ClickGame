package uk.ungureanu.clickgame.entities.game;

import uk.ungureanu.clickgame.entities.screen.ScreenEntity;

import java.util.LinkedList;
import java.util.List;

public interface GameEntity {
    // has-a ScreenEntity (Adapter pattern) from which it gets click events.
    // Therefore, decoupled from the geometric shape (or sprite / model) of the onscreen object
    ScreenEntity getScreenEntity();



    void onClick();
}
