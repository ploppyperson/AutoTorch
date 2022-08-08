package uk.antiperson.autotorch.gui;

import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.function.Consumer;

public class GuiItemStack {

    private final ItemStack itemStack;
    private Consumer<InventoryClickEvent> onClick;

    public GuiItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public GuiItemStack(ItemStack itemStack, String name, List<String> list) {
        this.itemStack = itemStack;
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.WHITE + name);
        itemMeta.setLore(list);
        this.itemStack.setItemMeta(itemMeta);
    }

    public GuiItemStack(GuiItemStack itemStack) {
        this(itemStack.getBukkit().clone());
    }

    public ItemStack getBukkit() {
        return itemStack;
    }

    public void setOnClick(Consumer<InventoryClickEvent> onClick) {
        this.onClick = onClick;
    }

    public Consumer<InventoryClickEvent> getOnClick() {
        return onClick;
    }
}
