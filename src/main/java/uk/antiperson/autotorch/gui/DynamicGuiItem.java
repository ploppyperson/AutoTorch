package uk.antiperson.autotorch.gui;

import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.Map;

public class DynamicGuiItem extends GuiItem {

    private final Map<Integer, GuiItemStack> items;
    private int showing;

    public DynamicGuiItem() {
        this.items = new HashMap<>();
    }

    void addItem(GuiItemStack itemStack) {
        addItem(getShowing() + 1, itemStack);
    }

    void addItem(int id, GuiItemStack itemStack) {
        items.put(id, itemStack);
    }

    GuiItemStack getAtId(int itemId) {
        return this.items.get(itemId);
    }

    int getShowing() {
        return showing;
    }

    void setShowing(int showing) {
        setUpdated(true);
        this.showing = showing;
        setItemStack(items.get(showing));
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        int nextShowing = getShowing() == items.size() - 1 ? 0 : getShowing() + 1;
        setShowing(nextShowing);
    }

    @Override
    public GuiItemStack getItemStack() {
        return getAtId(getShowing());
    }

    @Override
    public void setItemStack(GuiItemStack itemStack) {
        addItem(getShowing(), itemStack);
    }
}
