package uk.antiperson.autotorch.gui;

import org.bukkit.ChatColor;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EnumGuiItem extends DynamicGuiItem {

    private final Enum<?>[] values;

    public EnumGuiItem(Enum<?>[] values) {
        this.values = values;
    }

    public Enum<?> getValue() {
        return values[getShowing()];
    }

    public void populate(GuiItemStack guiItemStack) {
        for (Enum<?> value : values) {
            GuiItemStack guiItemStack1 = new GuiItemStack(guiItemStack);
            guiItemStack1.setOnClick(guiItemStack.getOnClick());
            ItemMeta itemMeta = guiItemStack1.getBukkit().getItemMeta();
            List<String> lore = itemMeta.getLore() == null ? new ArrayList<>() : itemMeta.getLore();
            for (int i = 0; i < lore.size(); i++) {
                String atIndex = lore.get(i);
                atIndex = atIndex.replaceAll("%enum%", ChatColor.GRAY + value.toString());
                lore.set(i, atIndex);
            }
            itemMeta.setLore(lore);
            guiItemStack1.getBukkit().setItemMeta(itemMeta);
            addItem(value.ordinal(), guiItemStack1);
        }
    }

    public void setShowing(Enum<?> value) {
        setShowing(value.ordinal());
    }
}
