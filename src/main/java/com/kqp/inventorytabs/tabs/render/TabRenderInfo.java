package com.kqp.inventorytabs.tabs.render;

import com.kqp.inventorytabs.tabs.tab.Tab;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * Data class that describes how a tab should be rendered.
 */
@Environment(EnvType.CLIENT)
public class TabRenderInfo {
    public Tab tabReference;
    public int index;
    public int x, y;
    public int texW, texH, texU, texV;
    public int itemX, itemY;
}
