package be.kdg.prog6.common.events;

import java.util.UUID;

public record AttractionChangedEvent(UUID attractionUUID, int currentVisitors, String throughput) {
}
