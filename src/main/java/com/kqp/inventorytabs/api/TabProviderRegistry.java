package com.kqp.inventorytabs.api;

import com.kqp.inventorytabs.init.InventoryTabs;
import com.kqp.inventorytabs.tabs.provider.*;
import com.kqp.inventorytabs.tabs.tab.PlayerInventoryTab;
import com.kqp.inventorytabs.tabs.tab.SimpleBlockTab;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Registry for tab providers.
 */
public class TabProviderRegistry {
    private static final Map<Identifier, TabProvider> TAB_PROVIDERS = new HashMap();

    public static final PlayerInventoryTabProvider PLAYER_INVENTORY_TAB_PROVIDER = (PlayerInventoryTabProvider) register(InventoryTabs.id("player_inventory_tab_provider"), new PlayerInventoryTabProvider());
    public static final SimpleBlockTabProvider SIMPLE_BLOCK_TAB_PROVIDER = (SimpleBlockTabProvider) register(InventoryTabs.id("simple_block_tab_provider"), new SimpleBlockTabProvider());
    public static final ChestTabProvider CHEST_TAB_PROVIDER = (ChestTabProvider) register(InventoryTabs.id("chest_tab_provider"), new ChestTabProvider());
    public static final EnderChestTabProvider ENDER_CHEST_TAB_PROVIDER = (EnderChestTabProvider) register(InventoryTabs.id("ender_chest_tab_provider"), new EnderChestTabProvider());
    public static final ShulkerBoxTabProvider SHULKER_BOX_TAB_PROVIDER = (ShulkerBoxTabProvider) register(InventoryTabs.id("shulker_box_tab_provider"), new ShulkerBoxTabProvider());

    public static void init() {
        addVanillaBlockTabProviders();
    }
    
    private static void addVanillaBlockTabProviders() {
        registerSimpleBlock(Blocks.FURNACE);
        registerSimpleBlock(Blocks.CARTOGRAPHY_TABLE);
        registerSimpleBlock(Blocks.CRAFTING_TABLE);
        registerSimpleBlock(Blocks.ENCHANTING_TABLE);
        registerSimpleBlock(Blocks.GRINDSTONE);
        registerSimpleBlock(Blocks.LOOM);
        registerSimpleBlock(Blocks.SMITHING_TABLE);
        registerSimpleBlock(Blocks.STONECUTTER);
        registerSimpleBlock(Blocks.BARREL);
        registerSimpleBlock(Blocks.BLAST_FURNACE);
        registerSimpleBlock(Blocks.SMOKER);
        registerSimpleBlock(Blocks.BREWING_STAND);
        registerSimpleBlock(Blocks.DISPENSER);
        registerSimpleBlock(Blocks.DROPPER);
        registerSimpleBlock(Blocks.HOPPER);

        Registry.BLOCK.forEach(block -> {
            if (block instanceof AnvilBlock) {
                registerSimpleBlock(block);
            }
        });
    }

    /**
     * Used to register a block with a simple block tab provider.
     * 
     * @param block
     */
    public static void registerSimpleBlock(Block block) {
        SIMPLE_BLOCK_TAB_PROVIDER.addBlock(block);
    }

    public static TabProvider register(Identifier id, TabProvider tabProvider) {
        TAB_PROVIDERS.put(id, tabProvider);

        return tabProvider;
    }

    public static List<TabProvider> getTabProviders() {
        return new ArrayList(TAB_PROVIDERS.values());
    }
}
