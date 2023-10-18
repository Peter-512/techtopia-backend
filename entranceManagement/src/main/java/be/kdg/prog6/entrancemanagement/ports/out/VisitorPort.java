package be.kdg.prog6.entrancemanagement.ports.out;

import be.kdg.prog6.entrancemanagement.domain.Ticket;
import be.kdg.prog6.entrancemanagement.domain.Visitor;

import java.util.Optional;

public interface VisitorPort {
	Optional<Visitor> loadVisitor(Ticket.TicketUUID ticketUUID);

	void saveVisitor(Visitor visitor);
}
