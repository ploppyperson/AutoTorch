package uk.antiperson.autotorch.gui;

public abstract class GuiItem implements Item {

    private GuiItemStack itemStack;
    private boolean updated;

    public GuiItem(GuiItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public GuiItem() {

    }

    public void setItemStack(GuiItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public GuiItemStack getItemStack() {
        return itemStack;
    }

    boolean isUpdated() {
        return updated;
    }

    void setUpdated(boolean updated) {
        this.updated = updated;
    }

}
