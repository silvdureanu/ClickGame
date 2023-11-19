package uk.ungureanu.clickgame.leaderboard;

import java.util.ArrayList;

public class SerialisableRanking {
    // A simple class designed as a compatibility layer between abstract Rankings and the storage layer
    // Necessary because gson serialisation (needed to store into redis) does not support interface types
    private ArrayList<String> playerNames;
    private ArrayList<Double> scores;
    public SerialisableRanking(Ranking ranking) {
        playerNames = new ArrayList<>();
        scores = new ArrayList<>();
        for (PlayerRecord player: ranking.getAllPlayers()) {
            playerNames.add(player.getName());
            scores.add(player.getScore());
        }
    }

    public ArrayList<String> getPlayerNames(){
        return playerNames;
    }

    public ArrayList<Double> getScores(){
        return scores;
    }

}
