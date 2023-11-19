package uk.ungureanu.clickgame.config;

import uk.ungureanu.clickgame.entities.game.ClassicGameEntity;
import uk.ungureanu.clickgame.entities.game.GameEntity;
import uk.ungureanu.clickgame.gamelogic.ClassicGameMaster;

import java.awt.geom.Area;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class NonOverlappingTiler implements Tiler {
    //May occasionally take a very long time to tile, as new items must be generated until they fit into a free space
    private final int windowWidth,windowHeight;
    private ClassicGameMaster gm;
    public NonOverlappingTiler(int width, int height, ClassicGameMaster gm) {
        this.windowWidth = width;
        this.windowHeight = height;
        this.gm = gm;
    }
    @Override
    public List<ClassicGameEntity> generateEntities() {
        final int numberElements = 30;
        int unclickables = 0;
        AvoidBuilder avoids = new AvoidBuilder(this.windowWidth, this.windowHeight, this.gm);
        ChangeBuilder changes = new ChangeBuilder(this.windowWidth, this.windowHeight, this.gm);
        CollectBuilder collects = new CollectBuilder(this.windowWidth, this.windowHeight, this.gm);
        List<ClassicGameEntity> entities = new LinkedList<>();
        boolean overlaps;
        boolean acceptedNew;
        boolean addedAvoid;
        Random rand = new Random();
        for(int i=0; i<numberElements; i++) {
            acceptedNew = false;
            while (!acceptedNew) {
                overlaps = false;
                addedAvoid = false;
                int which = rand.nextInt(3);
                ClassicGameEntity newEntity;
                //Too few branches for a case
                if (which == 0) {
                    newEntity = avoids.createEntity(5, 25);
                    addedAvoid = true;
                }
                else if (which == 1) {
                    newEntity = changes.createEntity(5, 25);
                }
                else { //which == 2
                        newEntity = collects.createEntity(5, 25);
                }

                for (ClassicGameEntity entity : entities) {
                    if (newEntity.getScreenEntity().intersects(entity.getScreenEntity().getBounds2D()))
                        overlaps = true;
                }
                if (!overlaps) {
                    acceptedNew = true;
                    entities.add(newEntity);
                    if(addedAvoid)
                        unclickables++;
                }
            }

        }
        this.injectTotalElements(numberElements,this.gm);
        this.injectUnclickables(unclickables,this.gm);
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
