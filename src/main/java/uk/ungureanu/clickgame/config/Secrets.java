package uk.ungureanu.clickgame.config;

public class Secrets {
    //Authentication token. The game currently assumes a valid connection for the rankings feature to work as expected.
    private static final String RedisCloudConnectionToken = "secret";
    public static String getRedisCloudConnectionToken() {
        return RedisCloudConnectionToken;
    }
}
