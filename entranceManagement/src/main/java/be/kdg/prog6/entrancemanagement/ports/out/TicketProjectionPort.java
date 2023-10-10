package be.kdg.prog6.entrancemanagement.ports.out;

import be.kdg.prog6.entrancemanagement.domain.Ticket;

import java.util.Optional;

public interface TicketProjectionPort {
	Optional<Ticket> loadTicket(Ticket.TicketUUID ticketUUID);

	void saveTicket(Ticket ticket);
}
