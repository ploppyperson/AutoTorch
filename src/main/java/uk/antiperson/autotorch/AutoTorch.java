package uk.antiperson.autotorch;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import uk.antiperson.autotorch.config.GlobalConfig;
import uk.antiperson.autotorch.config.PlayerConfig;
import uk.antiperson.autotorch.gui.GuiManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public final class AutoTorch extends JavaPlugin {

    private PlacerManager placerManager;
    private GlobalConfig globalConfig;
    private PlayerConfig playerConfig;
    private WorldGuardHandler worldGuardHandler;
    private GuiManager guiManager;

    public static final List<String> DEFAULT_LORE = Arrays.asList(ChatColor.WHITE + "Left click = +1", ChatColor.WHITE +
            "Right click = -1",ChatColor.WHITE + "Shift-Left click = +10", ChatColor.WHITE + "Shift-Right click = -10");

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("AutoTorch v" + getDescription().getVersion() + " by antiPerson");
        placerManager = new PlacerManager(this);
        globalConfig = new GlobalConfig(this);
        playerConfig = new PlayerConfig(this);
        guiManager = new GuiManager(this);
        guiManager.init();
        if (getServer().getPluginManager().getPlugin("WorldGuard") != null) {
            worldGuardHandler = new WorldGuardHandler();
        }
        try {
            globalConfig.init();
            playerConfig.init();
        } catch (IOException e) {
            getLogger().info("Error occurred while loading config.");
        }
        new PlaceTask(this).runTaskTimer(this, 5, getGlobalConfig().getTaskInterval());
        getCommand("autotorch").setExecutor(new GuiCommand(this));
        getCommand("autotorchadmin").setExecutor(new AdminCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getOnlinePlayers().forEach(player -> getGuiManager().stopWatching(player));
    }

    public PlacerManager getPlacerManager() {
        return placerManager;
    }

    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }

    public WorldGuardHandler getWorldGuardHandler() {
        return worldGuardHandler;
    }

    public PlayerConfig getPlayerConfig() {
        return playerConfig;
    }

    public GuiManager getGuiManager() {
        return guiManager;
    }
}
