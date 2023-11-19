package uk.ungureanu.clickgame.leaderboard;

import uk.ungureanu.clickgame.config.Configurator;

import java.util.ArrayList;

public class SimpleRanking implements Ranking {
    private ArrayList<PlayerRecord> ranking;
    public SimpleRanking(PlayerRecord record) {
        this.ranking = new ArrayList<>();
        this.ranking.add(record);
    }

    public SimpleRanking() {
        this.ranking = new ArrayList<>();
    }

    public SimpleRanking (SerialisableRanking serialRanking, Configurator config) {
        this.ranking = new ArrayList<>();
        for(int i=0; i<serialRanking.getPlayerNames().size(); i++)
            this.ranking.add(config.createPlayerRecord(serialRanking.getPlayerNames().get(i),serialRanking.getScores().get(i)));
    }

    @Override
    public ArrayList<PlayerRecord> getAllPlayers() {
        return this.ranking;
    }

    @Override
    public ArrayList<PlayerRecord> getTopPlayers(int n) {
        ArrayList<PlayerRecord> topRankers = new ArrayList<>();
        int numberRankers = this.ranking.size();
        // Iterate through first n, if they exist in the list
        for(int i=0; i<Math.min(numberRankers,n); i++) {
            topRankers.add(this.ranking.get(i));
        }
        return topRankers;
    }

    @Override
    public void insertRecord(PlayerRecord record) {
        boolean foundHigher = false;
        for (int i=0; i < this.ranking.size(); i++ ) {
            if (this.ranking.get(i).getScore() > record.getScore()) {
                foundHigher = true;
                this.ranking.add(i,record);
                break;
            }
        }
        //Last place in the rankings
        if (!foundHigher) {
            this.ranking.add(record);
        }
    }
}
