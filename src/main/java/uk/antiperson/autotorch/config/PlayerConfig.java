package uk.antiperson.autotorch.config;

import uk.antiperson.autotorch.AutoTorch;

import java.io.IOException;

public class PlayerConfig extends Configuration {

    public PlayerConfig(AutoTorch autoTorch) {
        super(autoTorch, "player.yml");
    }

    @Override
    public void init() throws IOException {
        super.init();
        addToEnumRegistry("take-torches-from", TorchLocation.class);
    }

    public int getRadius() {
        return getFileConfiguration().getInt("radius");
    }

    public int getMinLightLevel() {
        return getFileConfiguration().getInt("light-level");
    }

    public int getYMin() {
        return getFileConfiguration().getInt("y-min");
    }

    public int getYMax() {
        return getFileConfiguration().getInt("y-max");
    }

    public PlayerConfig.TorchLocation getTorchLocation() {
        return PlayerConfig.TorchLocation.valueOf(getFileConfiguration().getString("take-torches-from"));
    }

    public boolean isAttachToWalls() {
        return getFileConfiguration().getBoolean("attach-to-walls");
    }

    public enum TorchLocation {
        HAND,
        OFF_HAND,
        INVENTORY
    }
}
