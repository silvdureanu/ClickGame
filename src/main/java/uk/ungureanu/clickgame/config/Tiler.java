package uk.ungureanu.clickgame.config;

import uk.ungureanu.clickgame.entities.game.ClassicGameEntity;
import uk.ungureanu.clickgame.entities.game.GameEntity;
import uk.ungureanu.clickgame.gamelogic.ClassicGameMaster;

import java.util.List;

public interface Tiler {
    //Generates entities according to settings.
    List<ClassicGameEntity> generateEntities();
    void injectTotalElements(int totalElements, ClassicGameMaster gm);
    void injectUnclickables(int unclickables, ClassicGameMaster gm);
}
