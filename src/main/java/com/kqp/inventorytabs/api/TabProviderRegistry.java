package com.kqp.inventorytabs.api;

import com.kqp.inventorytabs.init.InventoryTabs;
import com.kqp.inventorytabs.tabs.provider.ChestTabProvider;
import com.kqp.inventorytabs.tabs.provider.EnderChestTabProvider;
import com.kqp.inventorytabs.tabs.provider.PlayerInventoryTabProvider;
import com.kqp.inventorytabs.tabs.provider.ShulkerBoxTabProvider;
import com.kqp.inventorytabs.tabs.provider.SimpleBlockTabProvider;
import com.kqp.inventorytabs.tabs.provider.TabProvider;
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

    public static final PlayerInventoryTabProvider PLAYER_INVENTORY_TAB_PROVIDER =
        (PlayerInventoryTabProvider) register(InventoryTabs.id("player_inventory_tab_provider"),
            new PlayerInventoryTabProvider());
    public static final SimpleBlockTabProvider SIMPLE_BLOCK_TAB_PROVIDER =
        (SimpleBlockTabProvider) register(InventoryTabs.id("simple_block_tab_provider"),
            new SimpleBlockTabProvider());
    public static final ChestTabProvider CHEST_TAB_PROVIDER =
        (ChestTabProvider) register(InventoryTabs.id("chest_tab_provider"), new ChestTabProvider());
    public static final EnderChestTabProvider ENDER_CHEST_TAB_PROVIDER =
        (EnderChestTabProvider) register(InventoryTabs.id("ender_chest_tab_provider"),
            new EnderChestTabProvider());
    public static final ShulkerBoxTabProvider SHULKER_BOX_TAB_PROVIDER =
        (ShulkerBoxTabProvider) register(InventoryTabs.id("shulker_box_tab_provider"),
            new ShulkerBoxTabProvider());

    public static void init() {
        addVanillaSimpleBlockTabProviders();
        addVanillaChestTabProviders();
        addModSimpleBlockTabProviders();
    }

    private static void addVanillaSimpleBlockTabProviders() {
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

    private static void addVanillaChestTabProviders() {
        registerChest(Blocks.CHEST);
        registerChest(Blocks.TRAPPED_CHEST);
    }

    private static void addModSimpleBlockTabProviders() {
        registerSimpleBlock(new Identifier("morecraftingtables", "warped_crafting_table"));
        registerSimpleBlock(new Identifier("morecraftingtables", "spruce_crafting_table"));
        registerSimpleBlock(new Identifier("morecraftingtables", "jungle_crafting_table"));
        registerSimpleBlock(new Identifier("morecraftingtables", "dark_oak_crafting_table"));
        registerSimpleBlock(new Identifier("morecraftingtables", "crimson_crafting_table"));
        registerSimpleBlock(new Identifier("morecraftingtables", "birch_crafting_table"));
        registerSimpleBlock(new Identifier("morecraftingtables", "acacia_crafting_table"));

        registerSimpleBlock(new Identifier("linkedstorage", "storageblock"));

        registerSimpleBlock(new Identifier("fabric-furnaces", "obsidian_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "nether_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "iron_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "gold_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "fabric_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "ethereal_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "end_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "emerald_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "diamond_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "crystal_obsidian_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "crystal_nether_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "crystal_iron_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "crystal_gold_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "crystal_fabric_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "crystal_ethereal_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "crystal_end_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "crystal_emerald_furnace"));
        registerSimpleBlock(new Identifier("fabric-furnaces", "crystal_diamond_furnace"));

        registerSimpleBlock(new Identifier("automated_crafting", "auto_crafter"));

        registerSimpleBlock(new Identifier("byg", "zelkova_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "witch_hazel_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "willow_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "skyris_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "redwood_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "rainbow_eucalyptus_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "pine_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "aspen_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "baobab_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "blue_enchanted_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "cherry_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "cika_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "cypress_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "ebony_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "fir_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "green_enchanted_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "holly_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "jacaranda_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "mahogany_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "mangrove_crafting_table"));
        registerSimpleBlock(new Identifier("byg", "maple_crafting_table"));

        registerSimpleBlock(new Identifier("better-nether", "crafting_table_crimson"));
        registerSimpleBlock(new Identifier("better-nether", "crafting_table_mushroom"));
        registerSimpleBlock(new Identifier("better-nether", "crafting_table_mushroom_fir"));
        registerSimpleBlock(new Identifier("better-nether", "crafting_table_reed"));
        registerSimpleBlock(new Identifier("better-nether", "crafting_table_rubeus"));
        registerSimpleBlock(new Identifier("better-nether", "crafting_table_stalagnate"));
        registerSimpleBlock(new Identifier("better-nether", "crafting_table_warped"));
        registerSimpleBlock(new Identifier("better-nether", "crafting_table_wart"));
        registerSimpleBlock(new Identifier("better-nether", "crafting_table_willow"));
        registerSimpleBlock(new Identifier("better-nether", "basalt_furnace"));
        registerSimpleBlock(new Identifier("better-nether", "blackstone_furnace"));
        registerSimpleBlock(new Identifier("better-nether", "netherrack_furnace"));
    }

    /**
     * Used to register a block with the simple block tab provider.
     *
     * @param block
     */
    public static void registerSimpleBlock(Block block) {
        SIMPLE_BLOCK_TAB_PROVIDER.addBlock(block);
    }

    /**
     * Used to register a block identifier with the simple block tab provider.
     *
     * @param blockId
     */
    public static void registerSimpleBlock(Identifier blockId) {
        SIMPLE_BLOCK_TAB_PROVIDER.addBlock(blockId);
    }

    /**
     * Used to register a chest with the chest tab provider.
     *
     * @param block
     */
    public static void registerChest(Block block) {
        CHEST_TAB_PROVIDER.addChestBlock(block);
    }

    /**
     * Used to register a chest with the chest tab provider.
     *
     * @param blockId
     */
    public static void registerChest(Identifier blockId) {
        CHEST_TAB_PROVIDER.addChestBlock(blockId);
    }

    public static TabProvider register(Identifier id, TabProvider tabProvider) {
        TAB_PROVIDERS.put(id, tabProvider);

        return tabProvider;
    }

    public static List<TabProvider> getTabProviders() {
        return new ArrayList(TAB_PROVIDERS.values());
    }
}
