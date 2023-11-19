package uk.ungureanu.clickgame.config;

import uk.ungureanu.clickgame.entities.game.GameEntity;

public interface EntityBuilder {
    GameEntity createEntity();

    GameEntity createEntity(double minSize, double maxSize);
}
