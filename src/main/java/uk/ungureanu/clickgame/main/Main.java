package uk.ungureanu.clickgame.main;

import uk.ungureanu.clickgame.gamescreens.MainScreen;
import uk.ungureanu.clickgame.storage.RedisConnection;


public class Main {
    public static void main(String[] args) {

        //Clear rankings for testing purposes
        //RedisConnection.getInstance().delKey("rankings");

        MainScreen.getInstance();



    }
}