package uk.ungureanu.clickgame.storage;

import java.util.Optional;

public interface SettingsStorage<StorageType> {
    Optional<StorageType> getSettings();

    void storeSettings(StorageType settingsList);

}
