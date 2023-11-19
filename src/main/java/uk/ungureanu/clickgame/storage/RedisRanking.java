package uk.ungureanu.clickgame.storage;

import uk.ungureanu.clickgame.leaderboard.Ranking;

import java.util.Optional;

public class RedisRanking implements RankingStorage {
    public RedisRanking(){}

    @Override
    public void storeRanking(Ranking ranking) {
        RedisConnection.getInstance().setRanking("rankings",ranking);
    }

    @Override
    public Optional<Ranking> getRanking() {
        return RedisConnection.getInstance().getRanking("rankings");
    }
}
