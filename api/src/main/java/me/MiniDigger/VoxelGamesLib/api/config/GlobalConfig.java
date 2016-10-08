package me.MiniDigger.VoxelGamesLib.api.config;

import javax.inject.Singleton;

/**
 * The config defines all configuration values (and the default values)
 */
@Singleton
public class GlobalConfig extends Config {

    public final int configVersion = 1;
    public int currentVersion = configVersion;

    public boolean useRoleSystem = true;

    /**
     * @return the default config, with all default settings
     */
    public static GlobalConfig getDefault() {
        return new GlobalConfig();
    }

    @Override
    public int getConfigVersion() {
        return configVersion;
    }

    @Override
    public int getCurrentVersion() {
        return currentVersion;
    }

    @Override
    public void setCurrentVersion(int currentVersion) {
        this.currentVersion = currentVersion;
    }
}