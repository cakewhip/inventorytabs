package com.kqp.inventorytabs.api;

import com.kqp.inventorytabs.init.InventoryTabs;
import com.kqp.inventorytabs.tabs.provider.*;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Registry for tab providers.
 */
public class TabProviderRegistry {
    private static final Map<Identifier, TabProvider> TAB_PROVIDERS = new HashMap();

    public static final TabProvider PLAYER_INVENTORY_TAB_PROVIDER = register(InventoryTabs.id("player_inventory_tab_provider"), new PlayerInventoryTabProvider());
    public static final TabProvider VANILLA_BLOCK_TAB_PROVIDER = register(InventoryTabs.id("vanilla_block_tab_provider"), new VanillaBlockTabProvider());
    public static final TabProvider CHEST_TAB_PROVIDER = register(InventoryTabs.id("chest_tab_provider"), new ChestTabProvider());
    public static final TabProvider ENDER_CHEST_TAB_PROVIDER = register(InventoryTabs.id("ender_chest_tab_provider"), new EnderChestTabProvider());

    public static void init() {
    }

    public static TabProvider register(Identifier id, TabProvider tabProvider) {
        TAB_PROVIDERS.put(id, tabProvider);

        return tabProvider;
    }

    public static List<TabProvider> getTabProviders() {
        return new ArrayList(TAB_PROVIDERS.values());
    }
}
