package com.kqp.inventorytabs.init;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Config(name = "inventory_tabs")
public class InventoryTabsConfig implements ConfigData {
    public boolean doSightChecksFlag = false;

    @Environment(EnvType.CLIENT)
    public boolean doSightChecks() {
        if (MinecraftClient.getInstance().isIntegratedServerRunning()) {
            return doSightChecksFlag;
        } else {
            return InventoryTabsServerConfig.doSightChecks;
        }
    }
}
