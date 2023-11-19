package uk.ungureanu.clickgame.entities.game;

import uk.ungureanu.clickgame.entities.screen.ScreenEntity;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public interface ClassicGameEntity extends GameEntity {
    // GameEntity which assumes the rules are in the same family as the "standard" ones, i.e. clickable
    // and non-clickable entities. Other rulesets may be implemented by instantiating different
    // types of GameEntities.

    boolean isClickable();
    void setScreenEntityColour(Color newColour);

    void checkByGameMaster();

    public static java.util.List<ScreenEntity> getScreenEntityList(java.util.List<ClassicGameEntity> gameEntityList) {
        List<ScreenEntity> screenEntities = new LinkedList<>();
        for (ClassicGameEntity gameEntity: gameEntityList) {
            screenEntities.add(gameEntity.getScreenEntity());
        }
        return screenEntities;
    }



}
