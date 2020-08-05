package com.kqp.inventorytabs.tabs.provider;

import com.kqp.inventorytabs.tabs.tab.Tab;
import net.minecraft.client.network.ClientPlayerEntity;

import java.util.List;

/**
 * Base interface for exposing tabs to the player.
 */
public interface TabProvider {
    void addAvailableTabs(ClientPlayerEntity player, List<Tab> tabs);
}
