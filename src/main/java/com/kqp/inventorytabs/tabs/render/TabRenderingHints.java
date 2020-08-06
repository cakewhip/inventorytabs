package com.kqp.inventorytabs.tabs.render;

/**
 * Provides hints to the tab renderer.
 */
public interface TabRenderingHints {
    default int getTopRowXOffset() {
        return 0;
    }

    default int getTopRowYOffset() {
        return 0;
    }

    default int getBottomRowXOffset() {
        return 0;
    }

    default int getBottomRowYOffset() {
        return 0;
    }
}
