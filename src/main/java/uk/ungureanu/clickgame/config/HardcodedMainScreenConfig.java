package uk.ungureanu.clickgame.config;

public class HardcodedMainScreenConfig implements MainScreenConfig {
    @Override
    public int getWidth() {
        return 1440;
    }

    @Override
    public int getHeight() {
        return 900;
    }

    @Override
    public int getFramerate() {
        return 120;
    }
}
