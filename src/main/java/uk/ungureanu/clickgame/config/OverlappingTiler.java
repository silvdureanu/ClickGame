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
    @Override
    public List<ClassicGameEntity> generateEntities() {
        final int numberElements = 40;
        int unclickables = 0;
        AvoidBuilder avoids = new AvoidBuilder(this.windowWidth, this.windowHeight, this.gm);
        ChangeBuilder changes = new ChangeBuilder(this.windowWidth, this.windowHeight, this.gm);
        CollectBuilder collects = new CollectBuilder(this.windowWidth, this.windowHeight, this.gm);
        List<ClassicGameEntity> entities = new LinkedList<>();
        Random rand = new Random();
        for(int i=0; i<numberElements; i++) {
            int which = rand.nextInt(3);
            // Too few branches for a case
            if (which == 0) {
                entities.add(avoids.createEntity(5, 25));
                unclickables++;
            }
            else if (which == 1) {
                entities.add(changes.createEntity(5, 25));
            }
            else { //which == 2
                entities.add(collects.createEntity(5, 25));
            }
        }
        this.injectTotalElements(numberElements,this.gm);
        this.injectUnclickables(unclickables,this.gm);
        System.out.println(unclickables);
        System.out.println(numberElements);
        return entities;
    }

    @Override
    public void injectTotalElements(int totalElements, ClassicGameMaster gm) {
        gm.setInitialNumberOfElements(totalElements);
    }

    @Override
    public void injectUnclickables(int unclickables, ClassicGameMaster gm) {
        gm.setUnclickables(unclickables);
    }


}
