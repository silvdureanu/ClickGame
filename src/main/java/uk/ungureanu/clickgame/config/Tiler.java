package uk.ungureanu.clickgame.config;

import uk.ungureanu.clickgame.entities.game.ClassicGameEntity;
import uk.ungureanu.clickgame.entities.game.GameEntity;

import java.util.List;

public interface Tiler {
    //Generates entities according to settings.
    List<ClassicGameEntity> generateEntities();
}
