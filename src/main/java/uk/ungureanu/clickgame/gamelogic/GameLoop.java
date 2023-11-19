package uk.ungureanu.clickgame.gamelogic;

import uk.ungureanu.clickgame.entities.game.ClassicGameEntity;
import uk.ungureanu.clickgame.entities.game.GameEntity;

public interface GameLoop {
    // Handles main loop logic. Decoupled from visuals(gamescreen).

    void checkState();
    //Have GameMaster check - useful if state can become illegal (e.g. collisions if entities are animated),
    // or if state needs to be updated at a certain interval, as seen with colour-changing shapes.
    void loop();

    int getTimer();

    void removeEntity(ClassicGameEntity entity);

    void end();







}
