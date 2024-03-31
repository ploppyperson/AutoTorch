package uk.antiperson.autotorch.config;

import org.bukkit.World;
import org.bukkit.block.Block;
import uk.antiperson.autotorch.AutoTorch;

import java.io.IOException;

public class GlobalConfig extends Configuration {

    public GlobalConfig(AutoTorch autoTorch) {
        super(autoTorch, "global.yml");
    }

    @Override
    public void init() throws IOException {
        super.init(true);
    }

    public int getTaskInterval() {
        return getFileConfiguration().getInt("task-interval");
    }

    public boolean isTorchesFromInventory() {
        return getFileConfiguration().getBoolean("torches-from-inventory");
    }

    public boolean isWorldBlacklisted(World world) {
        return getFileConfiguration().getStringList("world-blacklist").contains(world.getName());
    }

    public boolean isBlockTypeBlacklisted(Block block) {
        return getFileConfiguration().getStringList("block-type-blacklist").contains(block.getType().toString());
    }
}
