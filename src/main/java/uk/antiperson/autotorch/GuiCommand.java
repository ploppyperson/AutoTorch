package uk.antiperson.autotorch;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.antiperson.autotorch.config.PlayerTranslateConfig;
import uk.antiperson.autotorch.gui.Gui;

import java.io.IOException;

public class GuiCommand implements CommandExecutor {

    private final AutoTorch autoTorch;
    private final PlayerTranslateConfig ptc;

    public GuiCommand(AutoTorch autoTorch) {
        this.autoTorch = autoTorch;
        this.ptc = new PlayerTranslateConfig(autoTorch);
        try {
            ptc.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("autotorch.auto")) {
            sender.sendMessage("No permission");
            return false;
        }
        TorchPlacer torchPlacer = autoTorch.getPlacerManager().getTorchPlacer((Player) sender);
        Gui gui = new Gui((Player) sender);
        autoTorch.getGuiManager().watch(gui);
        ConfigGui configGui = new ConfigGui(torchPlacer.getPlayerConfig(), ptc);
        gui.addPage(0, configGui.createPage(gui.getPlayer()));
        gui.show();
        return false;
    }
}
