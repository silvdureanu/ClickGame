package uk.ungureanu.clickgame.gamelogic;

import uk.ungureanu.clickgame.entities.game.ChangingEntity;
import uk.ungureanu.clickgame.entities.game.ClassicGameEntity;
import uk.ungureanu.clickgame.entities.game.FixedEntity;

import java.util.List;

public interface ClassicGameMaster {
    // Checks state of the game and declares next state. May assign new instance mid-game to e.g. make the game harder.
    // The game rules do presuppose a certain form for the GameEntities, in this case the existence of
    //variable-state as well as strictly clickable and non-clickable ones, which is why it uses ClassicGameEntity
    // Despite this (natural) coupling between a family of entities and a family of rules, this setup does allow for
    // significantly more freedom, e.g.  different geometric shapes, colours, and switching
    // intervals for the same rules, as well as different rules (e.g. switching becomes twice as fast after 10 seconds,
    // the effects of clicking red or green reversed, etc.) for the same set of game entities.
    void clickCheck(ClassicGameEntity entity);

    void checkState(List<ClassicGameEntity> entities);

    //Visitor pattern, to enable different rules for different entity types
    void checkGameEntity(ChangingEntity entity);

    void checkGameEntity(FixedEntity entity);

    void setGameLoop(GameLoop loop);
}
