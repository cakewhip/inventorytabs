package com.kqp.inventorytabs.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class InventoryTabsServerConfig {
    public static boolean doSightChecks = true;

    public static void reset() {
        doSightChecks = true;
    }
}
