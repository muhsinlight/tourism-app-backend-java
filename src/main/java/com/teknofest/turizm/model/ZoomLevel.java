package com.teknofest.turizm.model;

public enum ZoomLevel {
    VERY_LOW(0, 5),
    LOW(6, 10),
    MEDIUM(11, 15),
    HIGH(16, 20);

    private final int minZoom;
    private final int maxZoom;

    ZoomLevel(int minZoom, int maxZoom) {
        this.minZoom = minZoom;
        this.maxZoom = maxZoom;
    }

    public int getMinZoom() {
        return minZoom;
    }

    public int getMaxZoom() {
        return maxZoom;
    }

    public static ZoomLevel fromLevel(int zoomLevel) {
        for (ZoomLevel level :  values()) {
            if (level.isInRange(zoomLevel)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Geçersiz zoom seviyesi: " + zoomLevel);
    }

    private boolean isInRange(int zoom) {
        return zoom >= minZoom && zoom <= maxZoom;
    }
}
