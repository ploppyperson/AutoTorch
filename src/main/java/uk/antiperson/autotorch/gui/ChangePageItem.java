package uk.antiperson.autotorch.gui;

import org.bukkit.event.inventory.InventoryClickEvent;

public class ChangePageItem extends GuiItem {

    private final Gui gui;
    private final int newPage;

    public ChangePageItem(Gui gui, int newPage) {
        this.gui = gui;
        this.newPage = newPage;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        gui.changePage(newPage);
    }
}
