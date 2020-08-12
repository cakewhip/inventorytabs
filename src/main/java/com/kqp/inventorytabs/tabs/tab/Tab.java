package com.kqp.inventorytabs.tabs.tab;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

/**
 * Base interface for tabs.
 */
@Environment(EnvType.CLIENT)
public interface Tab {
    /**
     * Fires whenever the tab is clicked.
     */
    void open();

    /**
     * Returns true if the tab should stop being displayed.
     * Should be synced up with the provider that provides this tab.
     *
     * @return
     */
    boolean shouldBeRemoved();

    /**
     * Returns the item stack that shows in the tab.
     *
     * @return
     */
    ItemStack getItemStack();

    /**
     * Returns the text that's displayed when hovering over the tab.
     *
     * @return
     */
    Text getHoverText();

    /**
     * Called when the screen associated with the tab is closed.
     */
    default void onClose() {
    }

    /**
     * Returns the tab's priority when being displayed.
     * The player's inventory is at 100.
     *
     * @return
     */
    default int getPriority() {
        return 0;
    }
}
