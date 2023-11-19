package uk.ungureanu.clickgame.leaderboard;

import java.util.List;

public interface Ranking {

    List<PlayerRecord> getAllPlayers();

    List<PlayerRecord> getTopPlayers(int n);

    void insertRecord(PlayerRecord record);
}
