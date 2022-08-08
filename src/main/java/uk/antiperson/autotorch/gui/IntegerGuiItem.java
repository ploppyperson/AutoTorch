package uk.antiperson.autotorch.gui;

import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;
import uk.antiperson.autotorch.AutoTorch;

import java.util.ArrayList;
import java.util.List;

public class IntegerGuiItem extends GuiItem {

    private int value;
    private final int minValue;
    private final int maxValue;
    private GuiItemStack currentItem;

    public IntegerGuiItem(int minValue, int maxValue, int value) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = value;
    }

    @Override
    public void onClick(InventoryClickEvent consumer) {
        switch (consumer.getClick()) {
            case LEFT:
                add(1);
                break;
            case SHIFT_LEFT:
                add(10);
                break;
            case RIGHT:
                add(-1);
                break;
            case SHIFT_RIGHT:
                add(-10);
                break;
        }
        setUpdated(true);
    }

    public void add(int amount) {
        int result = getValue() + amount;
        if (result <= maxValue && result >= minValue) {
            value = result;
        }
    }

    @Override
    public GuiItemStack getItemStack() {
        if (!isUpdated() && currentItem != null) {
            return currentItem;
        }
        GuiItemStack temp = currentItem;
        currentItem = new GuiItemStack(super.getItemStack());
        if (temp != null) {
            currentItem.setOnClick(temp.getOnClick());
        }
        ItemMeta itemMeta = currentItem.getBukkit().getItemMeta();
        List<String> lore = itemMeta.getLore() == null ? new ArrayList<>() : itemMeta.getLore();
        lore.add("");
        lore.addAll(AutoTorch.DEFAULT_LORE);
        for (int i = 0; i < lore.size(); i++) {
            String atIndex = lore.get(i);
            atIndex = atIndex.replaceAll("%size%", ChatColor.GRAY + "" + getValue());
            lore.set(i, atIndex);
        }
        itemMeta.setLore(lore);
        currentItem.getBukkit().setItemMeta(itemMeta);
        return currentItem;
    }

    public int getValue() {
        return value;
    }
}
