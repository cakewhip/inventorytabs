package com.kqp.inventorytabs.tabs.tab;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.StringRenderable;

/**
 * Base interface for tabs.
 */
@Environment(EnvType.CLIENT)
public interface Tab {
    /**
     * Fires whenever the tab is clicked.
     *
     * @param player
     */
    void open(ClientPlayerEntity player);

    /**
     * Returns true if the tab should stop being displayed.
     * Should be synced up with the provider that provides this tab.
     *
     * @param player
     * @return
     */
    boolean shouldBeRemoved(ClientPlayerEntity player);

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
    StringRenderable getHoverText();

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
