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
        GuiPage guiPage = new GuiPage(gui.getPlayer(), 1);
        ConfigGui configGui = new ConfigGui(torchPlacer.getPlayerConfig());
        gui.addPage(0, guiPage);
        gui.addPage(1, configGui.createPage(gui.getPlayer()));
        BooleanGuiItem booleanGuiItem = new BooleanGuiItem();
        GuiItemStack enabled = new GuiItemStack(new ItemStack(Material.GREEN_WOOL), "Automatic Torch Placing", Arrays.asList(ChatColor.GRAY + "Enabled"));
        enabled.setOnClick(click -> torchPlacer.setEnabled(false));
        GuiItemStack disabled = new GuiItemStack(new ItemStack(Material.RED_WOOL), "Automatic Torch Placing", Arrays.asList(ChatColor.GRAY + "Disabled"));
        disabled.setOnClick(click -> torchPlacer.setEnabled(true));
        booleanGuiItem.setTrueItem(enabled);
        booleanGuiItem.setFalseItem(disabled);
        booleanGuiItem.setDefaultState(torchPlacer.isEnabled());
        guiPage.putItem(0, booleanGuiItem);
        ChangePageItem changePageItem = new ChangePageItem(gui, 1);
        changePageItem.setItemStack(new GuiItemStack(new ItemStack(Material.FURNACE), "Settings", Arrays.asList(ChatColor.WHITE + "Modify torch placing settings")));
        guiPage.putItem(8, changePageItem);
        gui.show();
        return false;
    }
}
