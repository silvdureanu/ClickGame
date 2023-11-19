package uk.ungureanu.clickgame.leaderboard;

public class SimplePlayerRecord implements PlayerRecord {
    private final String playerName;
    private final double playerScore;

    public SimplePlayerRecord(String name, double score) {
        this.playerName = name;
        this.playerScore = score;
    }

    public String getName() {
        return this.playerName;
    }

    @Override
    public double getScore() {
        return this.playerScore;
    }
}
