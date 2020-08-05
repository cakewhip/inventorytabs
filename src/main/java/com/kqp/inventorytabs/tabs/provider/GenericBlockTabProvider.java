package com.kqp.inventorytabs.tabs.provider;

import com.kqp.inventorytabs.tabs.tab.Tab;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * Generic tab provider that exposes tabs based on nearby blocks.
 */
public abstract class GenericBlockTabProvider implements TabProvider {
    public static final int SEARCH_DISTANCE = 5;

    @Override
    public void addAvailableTabs(ClientPlayerEntity player, List<Tab> tabs) {
        World world = player.world;

        // TODO: make this better and check line of sight
        for (int x = -SEARCH_DISTANCE; x <= SEARCH_DISTANCE; x++) {
            for (int y = -SEARCH_DISTANCE; y <= SEARCH_DISTANCE; y++) {
                for (int z = -SEARCH_DISTANCE; z <= SEARCH_DISTANCE; z++) {
                    BlockPos blockPos = player.getBlockPos().add(x, y, z);

                    if (matches(world, blockPos)) {
                        Tab tab = createTab(world, blockPos);

                        if (!tabs.contains(tab)) {
                            tabs.add(tab);
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks to see if block at passsed block position matches criteria.
     *
     * @param world
     * @param pos
     * @return
     */
    public abstract boolean matches(World world, BlockPos pos);

    /**
     * Method to create tabs.
     *
     * @param world
     * @param pos
     * @return
     */
    public abstract Tab createTab(World world, BlockPos pos);
}
