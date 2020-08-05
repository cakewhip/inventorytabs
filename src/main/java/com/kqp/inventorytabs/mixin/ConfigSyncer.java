package com.kqp.inventorytabs.mixin;

import com.kqp.inventorytabs.init.InventoryTabs;
import com.kqp.inventorytabs.network.SyncClientConfigS2C;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class ConfigSyncer {
    @Inject(method = "onPlayerConnect", at = @At("RETURN"))
    public void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo callbackInfo) {
        SyncClientConfigS2C.sendToPlayer(player, InventoryTabs.getConfig().doSightChecksFlag);
    }
}
