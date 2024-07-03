package uk.antiperson.autotorch;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import uk.antiperson.autotorch.config.GlobalConfig;
import uk.antiperson.autotorch.gui.GuiManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public final class AutoTorch extends JavaPlugin {

    private PlacerManager placerManager;
    private GlobalConfig globalConfig;
    private WorldGuardHandler worldGuardHandler;
    private GuiManager guiManager;

    public static final List<String> DEFAULT_LORE = Arrays.asList("", ChatColor.WHITE + "Left click = +1", ChatColor.WHITE +
            "Right click = -1",ChatColor.WHITE + "Shift-Left click = +10", ChatColor.WHITE + "Shift-Right click = -10");

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("AutoTorch v" + getDescription().getVersion() + " by antiPerson");
        placerManager = new PlacerManager(this);
        globalConfig = new GlobalConfig(this);
        guiManager = new GuiManager(this);
        guiManager.init();
        if (getServer().getPluginManager().getPlugin("WorldGuard") != null) {
            worldGuardHandler = new WorldGuardHandler();
        }
        try {
            globalConfig.init();
        } catch (IOException e) {
            getLogger().info("Error occurred while loading config.");
        }
        getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        new PlaceTask(this).runTaskTimer(this, 5, getGlobalConfig().getTaskInterval());
        getCommand("autotorch").setExecutor(new GuiCommand(this));
        getCommand("autotorchadmin").setExecutor(new AdminCommand(this));
        new Metrics(this, 3186);
        Updater updater = new Updater(this,28904);
        updater.checkUpdate().whenComplete(((updateResult, throwable) -> {
            switch (updateResult.getResult()) {
                case NONE: getLogger().info("No update is currently available."); break;
                case ERROR: getLogger().info("There was an error while getting the latest update."); break;
                case AVAILABLE: getLogger().info("A new version is currently available. (" + updateResult.getNewVersion() + ")"); break;
            }
        }));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getOnlinePlayers().forEach(player -> {
            getGuiManager().stopWatching(player);
            TorchPlacer placer = getPlacerManager().getTorchPlacer(player);
            if (placer == null) return;
            getPlacerManager().remove(placer);
        });
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

    public GuiManager getGuiManager() {
        return guiManager;
    }
}
