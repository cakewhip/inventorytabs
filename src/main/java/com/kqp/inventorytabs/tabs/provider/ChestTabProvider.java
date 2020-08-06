package com.kqp.inventorytabs.tabs.provider;

import com.kqp.inventorytabs.tabs.tab.ChestTab;
import com.kqp.inventorytabs.tabs.tab.Tab;
import com.kqp.inventorytabs.util.ChestUtil;
import net.minecraft.block.*;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Provides tabs for chests.
 * Limits double chests to having only one tab and takes into account if it's blocked.
 */
public class ChestTabProvider extends BlockTabProvider {
    @Override
    public void addAvailableTabs(ClientPlayerEntity player, List<Tab> tabs) {
        super.addAvailableTabs(player, tabs);

        Set<ChestTab> tabsToRemove = new HashSet();

        List<ChestTab> chestTabs = tabs.stream()
                .filter(tab -> tab instanceof ChestTab)
                .map(tab -> (ChestTab) tab)
                .filter(tab -> tab.block == Blocks.CHEST || tab.block == Blocks.TRAPPED_CHEST)
                .collect(Collectors.toList());

        World world = player.world;

        // Add any chests that are blocked
        chestTabs.stream()
                .filter(tab -> ChestBlock.isChestBlocked(world, tab.blockPos))
                .forEach(tabsToRemove::add);

        for (int i = 0; i < chestTabs.size(); i++) {
            ChestTab tab = chestTabs.get(i);

            if (!tabsToRemove.contains(tab)) {
                if (ChestUtil.isDouble(world, tab.blockPos)) {
                    tabsToRemove.add(new ChestTab(tab.block, ChestUtil.getOtherChestBlockPos(world, tab.blockPos)));
                }
            }
        }

        tabs.removeAll(tabsToRemove);
    }

    @Override
    public boolean matches(World world, BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();
        return block == Blocks.CHEST || block == Blocks.TRAPPED_CHEST;
    }

    @Override
    public Tab createTab(World world, BlockPos pos) {
        return new ChestTab(world.getBlockState(pos).getBlock(), pos);
    }
}
