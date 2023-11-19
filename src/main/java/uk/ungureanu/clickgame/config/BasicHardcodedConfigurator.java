package uk.ungureanu.clickgame.config;

import uk.ungureanu.clickgame.entities.game.ClassicGameEntity;
import uk.ungureanu.clickgame.entities.game.GameEntity;
import uk.ungureanu.clickgame.entities.screen.ScreenEntity;
import uk.ungureanu.clickgame.gamelogic.BasicClassicGameMaster;
import uk.ungureanu.clickgame.gamelogic.ClassicGameLoop;
import uk.ungureanu.clickgame.gamelogic.ClassicGameMaster;
import uk.ungureanu.clickgame.gamelogic.GameLoop;
import uk.ungureanu.clickgame.gamescreens.MainScreen;
import uk.ungureanu.clickgame.gamescreens.panels.*;
import uk.ungureanu.clickgame.leaderboard.*;
import uk.ungureanu.clickgame.storage.RankingStorage;
import uk.ungureanu.clickgame.storage.RedisRanking;

import java.util.List;

public class BasicHardcodedConfigurator implements Configurator {
    // Concrete 'Abstract factory'

    @Override
    public Tiler createTiler(int width, int height, ClassicGameMaster gameMaster) {
        //Ensure shapes aren't created too close to the margins, going out of the frame
        return new NonOverlappingTiler(width-100,height-250,gameMaster);
    }

    @Override
    public PlayerRecord createPlayerRecord(String name, double score) {
        return new SimplePlayerRecord(name, score);
    }

    @Override
    public Ranking createRanking() {
        return new SimpleRanking();
    }

    @Override
    public Ranking createRankingWithRecord(PlayerRecord record) {
        return new SimpleRanking(record);
    }

    @Override
    public Ranking createRankingFromSerialisable(SerialisableRanking serialised) {
        return new SimpleRanking(serialised, this);
    }

    @Override
    public RankingStorage createRankingStorage() {
        return new RedisRanking();
    }

    @Override
    public GameLoop createGameLoop(ClassicGameMaster gameMaster, List<ClassicGameEntity> gameEntities, GameplayScreen gameplayScreen) {
        GameLoop loop = new ClassicGameLoop(gameEntities, gameMaster, gameplayScreen);
        gameMaster.setGameLoop(loop);
        return loop;
    }

    @Override
    public ClassicGameMaster createGameMaster() {
        return new BasicClassicGameMaster();
    }

    @Override
    public NewGameScreen createNewGameScreen() {
        return new BasicNewGameScreen();
    }

    @Override
    public GameplayScreen createGameplayScreen(List<ScreenEntity> screenEntities) {
        return new BasicGameplayScreen(screenEntities);
    }

    @Override
    public FailGameScreen createFailGameScreen() {
        return new BasicFailGameScreen();
    }

    @Override
    public ScoreScreen createScoreScreen(int finishTime) {
        return new BasicScoreScreen(finishTime);
    }

    //Abstract factory that instantiates various components based on hardcoded values
    //Default fallback if retrieval from database/disk fails or is not performed
    @Override
    public MainScreenConfig getMainScreenSettings() {
        return new HardcodedMainScreenConfig();
    }




}
