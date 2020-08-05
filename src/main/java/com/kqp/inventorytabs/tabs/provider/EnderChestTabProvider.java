package com.kqp.inventorytabs.tabs.provider;

import com.kqp.inventorytabs.tabs.tab.GenericBlockTab;
import com.kqp.inventorytabs.tabs.tab.Tab;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * Provides tabs for ender chests.
 * Limits amount of ender chest tabs to only one.
 */
public class EnderChestTabProvider extends GenericBlockTabProvider {
    @Override
    public void addAvailableTabs(ClientPlayerEntity player, List<Tab> tabs) {
        for (int i = 0; i < tabs.size(); i++) {
            Tab tab = tabs.get(i);
            if (tab instanceof GenericBlockTab && ((GenericBlockTab) tab).block == Blocks.ENDER_CHEST) {
                return;
            }
        }

        super.addAvailableTabs(player, tabs);
    }

    @Override
    public boolean matches(World world, BlockPos pos) {
        return world.getBlockState(pos).getBlock() == Blocks.ENDER_CHEST;
    }

    @Override
    public Tab createTab(World world, BlockPos pos) {
        return new GenericBlockTab(Blocks.ENDER_CHEST, pos);
    }
}
