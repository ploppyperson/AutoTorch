package uk.antiperson.autotorch.gui;

import org.bukkit.entity.Player;
import uk.antiperson.autotorch.AutoTorch;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuiManager {

    private Map<UUID, Gui> map;
    private AutoTorch autoTorch;

    public GuiManager(AutoTorch autoTorch) {
        this.autoTorch = autoTorch;
        map = new HashMap<>();
    }

    public void init() {
        autoTorch.getServer().getPluginManager().registerEvents(new GuiListeners(this), autoTorch);
    }

    public void watch(Gui gui) {
        map.put(gui.getPlayer().getUniqueId(), gui);
    }

    public void stopWatching(Player player) {
        map.remove(player.getUniqueId());
    }

    public Gui getGui(Player player) {
        return map.get(player.getUniqueId());
    }
}
