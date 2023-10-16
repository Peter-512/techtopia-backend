package be.kdg.prog6.common.events;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public record TicketBoughtEvent(UUID ticketUUID, LocalDate validOn) implements Event, Serializable {
}
