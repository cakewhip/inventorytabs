package com.kqp.inventorytabs.tabs.provider;

import com.kqp.inventorytabs.tabs.tab.*;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

/**
 * Provides tabs for simple blocks.
 */
public class SimpleBlockTabProvider extends BlockTabProvider {
    private Set<Block> blocks = new HashSet();

    public SimpleBlockTabProvider() {
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public void removeBlock(Block block) {
        blocks.remove(block);
    }

    public Set<Block> getBlocks() {
        return this.blocks;
    }

    @Override
    public boolean matches(World world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);

        if (blocks.contains(blockState.getBlock())) {
            return true;
        }

        return false;
    }

    @Override
    public Tab createTab(World world, BlockPos pos) {
        return new SimpleBlockTab(world.getBlockState(pos).getBlock(), pos);
    }
}
