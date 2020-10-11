package com.kqp.inventorytabs.mixin.client;

import com.kqp.inventorytabs.init.InventoryTabsServerConfig;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class ServerConfigResetter {
    @Inject(
        method = "disconnect",
        at = @At("TAIL")
    )
    private void resetServerConfig(CallbackInfo callbackInfo) {
        InventoryTabsServerConfig.reset();
    }
}
