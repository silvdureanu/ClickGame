package uk.ungureanu.clickgame.config;

import uk.ungureanu.clickgame.entities.game.ClassicGameEntity;
import uk.ungureanu.clickgame.entities.game.GameEntity;
import uk.ungureanu.clickgame.entities.screen.ScreenEntity;
import uk.ungureanu.clickgame.gamelogic.ClassicGameMaster;
import uk.ungureanu.clickgame.gamelogic.GameLoop;
import uk.ungureanu.clickgame.gamescreens.panels.FailGameScreen;
import uk.ungureanu.clickgame.gamescreens.panels.GameplayScreen;
import uk.ungureanu.clickgame.gamescreens.panels.NewGameScreen;
import uk.ungureanu.clickgame.gamescreens.panels.ScoreScreen;
import uk.ungureanu.clickgame.leaderboard.PlayerRecord;
import uk.ungureanu.clickgame.leaderboard.Ranking;
import uk.ungureanu.clickgame.leaderboard.SerialisableRanking;
import uk.ungureanu.clickgame.storage.RankingStorage;

import java.util.List;

public interface Configurator {

    //Abstract factory that instantiates various components based on desired configuration
    public Tiler createTiler(int width, int height, ClassicGameMaster gameMaster);
//    public RankingStorage createRankingStorage();
    public PlayerRecord createPlayerRecord(String name, double score);
    public Ranking createRanking();
    public Ranking createRankingWithRecord(PlayerRecord record);
    public Ranking createRankingFromSerialisable(SerialisableRanking serialised);
    public RankingStorage createRankingStorage();
    public GameLoop createGameLoop(ClassicGameMaster gameMaster, List<ClassicGameEntity> gameEntities, GameplayScreen gameplayScreen);
    public ClassicGameMaster createGameMaster();
    public NewGameScreen createNewGameScreen();
    public GameplayScreen createGameplayScreen(List<ScreenEntity> screenEntities);
    public FailGameScreen createFailGameScreen();
    public ScoreScreen createScoreScreen(int finishTime);
    public MainScreenConfig getMainScreenSettings();
    //MainScreen constructor is private(Singleton), so must pass in custom settings, rather than inject a prebuilt
}
