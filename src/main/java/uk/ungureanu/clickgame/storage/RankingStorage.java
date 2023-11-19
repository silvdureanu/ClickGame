package uk.ungureanu.clickgame.storage;

import uk.ungureanu.clickgame.leaderboard.Ranking;

import java.util.Optional;

public interface RankingStorage {
    Optional<Ranking> getRanking();

    void storeRanking(Ranking ranking);

}
