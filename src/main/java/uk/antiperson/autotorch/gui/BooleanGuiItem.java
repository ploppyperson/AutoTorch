package uk.antiperson.autotorch.gui;

public class BooleanGuiItem extends DynamicGuiItem {

    public void setFalseItem(GuiItemStack itemStack) {
        addItem(0, itemStack);
    }

    public void setTrueItem(GuiItemStack itemStack) {
        addItem(1, itemStack);
    }

    public void setDefaultState(boolean state) {
        if (state) {
            setShowing(1);
            return;
        }
        setShowing(0);
    }
}
