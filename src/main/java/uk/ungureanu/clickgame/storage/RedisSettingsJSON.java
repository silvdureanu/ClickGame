package uk.ungureanu.clickgame.storage;

import java.util.Optional;

public class RedisSettingsJSON implements SettingsStorage<String> {
    private String setting;
    public RedisSettingsJSON(){}

    @Override
    public Optional<String> getSettings() {
        return RedisConnection.getInstance().getString("settings");
    }

    public void storeSettings(String setting) {
        RedisConnection.getInstance().setString("settings", setting);
    }
}
