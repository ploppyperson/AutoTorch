package uk.antiperson.autotorch.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.function.Consumer;

public class GuiListeners implements Listener {

    private final GuiManager guiManager;

    public GuiListeners(GuiManager guiManager) {
        this.guiManager = guiManager;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Gui gui = guiManager.getGui(player);
        if (gui == null) {
            return;
        }
        if (!gui.getOpenPage().getInventory().equals(event.getInventory())) {
            return;
        }
        event.setCancelled(true);
        GuiPage guiPage = gui.getOpenPage();
        GuiItem guiItem = guiPage.getItem(event.getSlot());
        if (guiItem == null) {
            return;
        }
        GuiItemStack guiItemStack = guiItem.getItemStack();
        guiItem.onClick(event);
        Consumer<InventoryClickEvent> consumer = guiItemStack.getOnClick();
        if (consumer != null) {
            consumer.accept(event);
        }
        guiPage.refresh();
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Gui gui = guiManager.getGui(player);
        if (gui == null) {
            return;
        }
        if (gui.isPageChanged()) {
            gui.setPageChanged(false);
            return;
        }
        guiManager.stopWatching(player);
    }

}
