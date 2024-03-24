package uk.antiperson.autotorch;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import uk.antiperson.autotorch.gui.BooleanGuiItem;
import uk.antiperson.autotorch.gui.ChangePageItem;
import uk.antiperson.autotorch.gui.Gui;
import uk.antiperson.autotorch.gui.GuiItemStack;
import uk.antiperson.autotorch.gui.GuiPage;

import java.util.Arrays;

public class GuiCommand implements CommandExecutor {

    private final AutoTorch autoTorch;

    public GuiCommand(AutoTorch autoTorch) {
        this.autoTorch = autoTorch;
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
        ConfigGui configGui = new ConfigGui(torchPlacer.getPlayerConfig());
        gui.addPage(0, configGui.createPage(gui.getPlayer()));
        gui.show();
        return false;
    }
}
