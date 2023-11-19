package uk.ungureanu.clickgame.gamelogic;

import uk.ungureanu.clickgame.config.Configurator;
import uk.ungureanu.clickgame.entities.game.ChangingEntity;
import uk.ungureanu.clickgame.entities.game.ClassicGameEntity;
import uk.ungureanu.clickgame.entities.game.FixedEntity;
import uk.ungureanu.clickgame.entities.game.GameEntity;
import uk.ungureanu.clickgame.gamescreens.MainScreen;
import uk.ungureanu.clickgame.gamescreens.panels.FailGameScreen;
import uk.ungureanu.clickgame.gamescreens.panels.GameplayScreen;
import uk.ungureanu.clickgame.gamescreens.panels.ScoreScreen;

import java.awt.*;
import java.util.List;

public class BasicClassicGameMaster implements ClassicGameMaster {
    private GameLoop loop;
    private int removedElements = 0;
    private boolean countedElements = false;
    private int unclickables = 0;
    private int allElements = 0;

    public BasicClassicGameMaster() {

    }
    @Override
    public void clickCheck(ClassicGameEntity entity) {
        if(entity.isClickable()) {
            //Remove entity
            this.loop.removeEntity(entity);
            this.removedElements++;
        }

        else {
            //GAME OVER
            this.loop.end();
            Configurator config = MainScreen.getInstance().getConfig();
            FailGameScreen failGameScreen = config.createFailGameScreen();
            MainScreen.getInstance().setPanel(failGameScreen);
        }

        if(removedElements + unclickables == allElements) {
            //Removed all elements
            this.loop.end();
            Configurator config = MainScreen.getInstance().getConfig();
            ScoreScreen scoreScreen = config.createScoreScreen(this.loop.getTimer());
            MainScreen.getInstance().setPanel(scoreScreen);
        }

    }

    @Override
    public void checkState(List<ClassicGameEntity> entities) {
        //Visitor pattern, allows different rules for different entity types
        //TODO replace list of entities with EntityCollection object, so we know from the start how many of each
        for(ClassicGameEntity entity: entities) {
            entity.checkByGameMaster();
            if(!countedElements)
                allElements++;
        }
        countedElements = true;
    }

     @Override
    public void checkGameEntity(ChangingEntity entity) {
        //Visitor checking changing entities

        int switchingState = (this.loop.getTimer() / entity.getSwitchInterval())%2;

        boolean newStateIsClickable = (switchingState == 0) == entity.getStartingState();

        if (newStateIsClickable != entity.isClickable()) {
            entity.setClickable(newStateIsClickable);
            if(newStateIsClickable)
                entity.setScreenEntityColour(Color.GREEN);
            else
                entity.setScreenEntityColour(Color.RED);
        }

    }

    @Override
    public void checkGameEntity(FixedEntity entity) {
        //Visitor checking the state of fixed entities - nothing to do in this case
        if(!countedElements && !entity.isClickable())
            unclickables++;
    }

    @Override
    public void setGameLoop(GameLoop loop) {
        this.loop = loop;
    }
}
