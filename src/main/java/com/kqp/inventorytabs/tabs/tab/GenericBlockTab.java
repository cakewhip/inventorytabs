package com.kqp.inventorytabs.tabs.tab;

import com.kqp.inventorytabs.init.InventoryTabs;
import com.kqp.inventorytabs.tabs.provider.GenericBlockTabProvider;
import com.kqp.inventorytabs.util.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.StringRenderable;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Objects;

/**
 * Generic tab for blocks.
 */
public class GenericBlockTab implements Tab {
    public final Block block;
    public final BlockPos blockPos;

    public GenericBlockTab(Block block, BlockPos blockPos) {
        this.block = block;
        this.blockPos = blockPos;
    }

    @Override
    public void open() {
        MinecraftClient client = MinecraftClient.getInstance();
        BlockHitResult hitResult = null;

        if (InventoryTabs.getConfig().doSightChecks()) {
            hitResult = BlockUtil.getLineOfSight(blockPos, client.player, 5D);
        } else {
            hitResult = new BlockHitResult(
                    client.player.getPos(),
                    Direction.EAST,
                    blockPos,
                    false
            );
        }

        MinecraftClient.getInstance().interactionManager.interactBlock(
                client.player,
                client.player.clientWorld,
                Hand.MAIN_HAND,
                hitResult
        );
    }

    @Override
    public boolean shouldBeRemoved() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (player.world.getBlockState(blockPos).getBlock() != block) {
            return true;
        }

        if (InventoryTabs.getConfig().doSightChecks()) {
            if (BlockUtil.getLineOfSight(blockPos, player, 5D) == null) {
                return true;
            }
        }

        Vec3d playerHead = player.getPos().add(0D, player.getEyeHeight(player.getPose()), 0D);

        if (getBlockVec3d().subtract(playerHead).lengthSquared() > GenericBlockTabProvider.SEARCH_DISTANCE * GenericBlockTabProvider.SEARCH_DISTANCE) {
            return true;
        }

        return false;
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(MinecraftClient.getInstance().world.getBlockState(blockPos).getBlock());
    }

    @Override
    public StringRenderable getHoverText() {
        World world = MinecraftClient.getInstance().world;

        BlockEntity blockEntity = world.getBlockEntity(blockPos);

        if (blockEntity != null) {
            CompoundTag tag = new CompoundTag();
            blockEntity.toTag(tag);

            if (tag.contains("CustomName", 8)) {
                return Text.Serializer.fromJson(tag.getString("CustomName"));
            }
        }

        return new TranslatableText(world.getBlockState(blockPos).getBlock().getTranslationKey());
    }

    private Vec3d getBlockVec3d() {
        return new Vec3d(
                blockPos.getX() + 0.5D,
                blockPos.getY() + 0.5D,
                blockPos.getZ() + 0.5D
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericBlockTab tab = (GenericBlockTab) o;
        return Objects.equals(blockPos, tab.blockPos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockPos);
    }
}
