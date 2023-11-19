package uk.ungureanu.clickgame.storage;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;
import uk.ungureanu.clickgame.gamescreens.MainScreen;
import uk.ungureanu.clickgame.leaderboard.Ranking;
import uk.ungureanu.clickgame.leaderboard.SerialisableRanking;
import uk.ungureanu.clickgame.leaderboard.SimpleRanking;

import java.util.Optional;


public final class RedisConnection {
    //Singleton connection to cloud database
    private static RedisConnection remoteConnection = null;
    private Jedis handler;
    private RedisConnection() {
        try {
            this.handler = new Jedis("redis://game-player:mxe8xez0uck!wjw.MXU@redis-11266.c55.eu-central-1-1.ec2.cloud.redislabs.com:11266");
        }
        catch (Exception e) {
            this.handler = null;
        }
    }

    public static RedisConnection getInstance(){
        if (RedisConnection.remoteConnection == null) {
            RedisConnection.remoteConnection = new RedisConnection();
        }
        return RedisConnection.remoteConnection;
    }
    public Optional<String> getString(String key) {
        String remoteString = null;
        try {
            remoteString = remoteConnection.handler.get(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error retrieving from database!");
            return Optional.ofNullable(null);
        }
        return Optional.ofNullable(remoteString);
    }

    public Optional<Ranking> getRanking(String key) {
        Ranking originalRanking;
        String remoteObjectString = null;
        try {
            remoteObjectString = remoteConnection.handler.get(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error retrieving from database!");
            return Optional.ofNullable(null);
        }
        Gson gson = new Gson();
        //Retrieve basic serial ranking and cast it to current Ranking+PlayerRecord implementation
        SerialisableRanking originalSerialRanking = gson.fromJson(remoteObjectString,SerialisableRanking.class);
        // Preserve nulls and handle them in a better way downstream
        if (originalSerialRanking == null)
            originalRanking = null;
        else
            originalRanking = MainScreen.getInstance().getConfig().createRankingFromSerialisable(originalSerialRanking);
        return Optional.ofNullable(originalRanking);
    }

    public void setString(String key, String val) {
        try {
            this.handler.set(key, val);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    public void setRanking(String key, Ranking obj) {
        Gson gson = new Gson();
        //Cast Ranking to SerialisableRanking in order to store it
        String json_object = gson.toJson(new SerialisableRanking(obj));
        try {
            this.handler.set(key, json_object);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    public void delKey(String key) {
        try {
            this.handler.del(key);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}
