package be.kdg.prog6.entrancemanagement.ports.in;

import be.kdg.prog6.entrancemanagement.domain.Ticket;

import java.time.LocalDate;

public record SaveTicketCommand(Ticket.TicketUUID ticketUUID, LocalDate validOn) {
}
