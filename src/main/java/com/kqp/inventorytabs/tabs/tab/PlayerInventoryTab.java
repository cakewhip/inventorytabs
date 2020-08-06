package com.kqp.inventorytabs.tabs.tab;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.text.StringRenderable;

/**
 * Tab for the player's inventory.
 */
public class PlayerInventoryTab implements Tab {
    @Override
    public void open() {
        MinecraftClient client = MinecraftClient.getInstance();
        client.openScreen(new InventoryScreen(client.player));
    }

    @Override
    public boolean shouldBeRemoved() {
        return false;
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Items.STICK);
    }

    @Override
    public StringRenderable getHoverText() {
        return new LiteralText("Inventory");
    }

    @Override
    public String toString() {
        return "PLAYER INVENTORY TAB";
    }

    @Override
    public int getPriority() {
        return 100;
    }
}
