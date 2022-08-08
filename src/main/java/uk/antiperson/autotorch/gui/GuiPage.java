package uk.antiperson.autotorch.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class GuiPage {

    private final Inventory inventory;
    private final Player player;
    private Map<Integer, GuiItem> map;

    public GuiPage(Player player, int rows) {
        this.player = player;
        this.inventory = Bukkit.createInventory(player, rows * 9, "AutoTorch");
        this.map = new HashMap<>();
    }

    public void putItem(int slot, GuiItem guiItem) {
        map.put(slot, guiItem);
        inventory.setItem(slot, guiItem.getItemStack().getBukkit());
    }

    public GuiItem getItem(int slot) {
        return map.get(slot);
    }

    public void refresh() {
        for (Map.Entry<Integer, GuiItem> entry : map.entrySet()) {
            if (!entry.getValue().isUpdated()) {
                continue;
            }
            inventory.setItem(entry.getKey(), entry.getValue().getItemStack().getBukkit());
            entry.getValue().setUpdated(false);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}
