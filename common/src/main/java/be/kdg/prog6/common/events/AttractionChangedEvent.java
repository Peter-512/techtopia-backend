package be.kdg.prog6.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record AttractionChangedEvent(UUID attractionUUID, LocalDateTime pit, int waitingTime) implements Event {
}
