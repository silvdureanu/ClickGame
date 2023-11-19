package uk.ungureanu.clickgame.config;

import uk.ungureanu.clickgame.entities.game.ClassicGameEntity;
import uk.ungureanu.clickgame.entities.game.GameEntity;
import uk.ungureanu.clickgame.gamelogic.ClassicGameMaster;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class OverlappingTiler implements Tiler {
    //Simpler than non-overlapping; need to ensure click checks are done in reverse order of drawing.
    //Note that this may result in unwinnable configurations
    private final int windowWidth,windowHeight;
    private ClassicGameMaster gm;
    public OverlappingTiler(int width, int height, ClassicGameMaster gm) {
        this.windowWidth = width;
        this.windowHeight = height;
        this.gm = gm;
    }

    public List<ClassicGameEntity> generateEntities() {
        AvoidBuilder avoids = new AvoidBuilder(this.windowWidth, this.windowHeight, this.gm);
        ChangeBuilder changes = new ChangeBuilder(this.windowWidth, this.windowHeight, this.gm);
        CollectBuilder collects = new CollectBuilder(this.windowWidth, this.windowHeight, this.gm);
        List<ClassicGameEntity> entities = new LinkedList<>();
        Random rand = new Random();
        for(int i=0; i<40; i++) {
            int which = rand.nextInt(3);
            switch(which) {
                case 0:
                    entities.add(avoids.createEntity(5,25));
                case 1:
                    entities.add(changes.createEntity(5,25));
                case 2:
                    entities.add(collects.createEntity(5,25));
            }
        }
        return entities;
    }


}
