package be.kdg.prog6.staffing.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record GateInteractionActivity(GateInteractionAction action, UUID ticketUUID, LocalDateTime pit) {
}
