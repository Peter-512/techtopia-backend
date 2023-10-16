package be.kdg.prog6.parkticketing.events;

import java.time.LocalDate;
import java.util.UUID;

public record TicketBoughtEvent(UUID ticketUUID, LocalDate validOn) {
}