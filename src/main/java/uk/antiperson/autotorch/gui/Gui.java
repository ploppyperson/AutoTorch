package uk.antiperson.autotorch.gui;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Gui {

    private Player player;
    private Map<Integer, GuiPage> guiPageMap;
    private int showingPage;

    public Gui(Player player) {
        this.player = player;
        this.guiPageMap = new HashMap<>();
    }

    public void addPage(int id, GuiPage guiPage) {
        guiPageMap.put(id, guiPage);
    }

    public int getShownPage() {
        return showingPage;
    }

    public GuiPage getOpenPage() {
        return guiPageMap.get(getShownPage());
    }

    public void show() {
        getPlayer().openInventory(getOpenPage().getInventory());
    }

    public void changePage(int pageId) {
        showingPage = pageId;
        show();
    }

    public Player getPlayer() {
        return player;
    }
}