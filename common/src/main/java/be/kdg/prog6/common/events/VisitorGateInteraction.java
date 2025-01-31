package be.kdg.prog6.common.events;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record VisitorGateInteraction(UUID ticketUUID, UUID gateUUID, LocalDateTime pit,
                                     String action) implements Event, Serializable {
}
