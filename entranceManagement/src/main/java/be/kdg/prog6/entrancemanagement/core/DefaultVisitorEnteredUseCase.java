package be.kdg.prog6.entrancemanagement.core;

import be.kdg.prog6.entrancemanagement.domain.Ticket;
import be.kdg.prog6.entrancemanagement.domain.Visitor;
import be.kdg.prog6.entrancemanagement.ports.in.VisitorEnteredUseCase;
import be.kdg.prog6.entrancemanagement.ports.out.TicketProjectionPort;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorProjectionPort;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorUpdatePort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class DefaultVisitorEnteredUseCase implements VisitorEnteredUseCase {
	private final TicketProjectionPort ticketProjectionPort;
	private final VisitorProjectionPort visitorProjectionPort;
	private final List<VisitorUpdatePort> visitorUpdatePorts;

	@Override
	@Transactional
	public boolean visitorEntered(UUID ticketUUID, UUID gateUUID) {
		var optionalTicket = ticketProjectionPort.loadTicket(new Ticket.TicketUUID(ticketUUID));
		if (optionalTicket.isEmpty()) {
			log.warn("Ticket with uuid {} not found", ticketUUID);
			return false;
		}

		var ticket = optionalTicket.get();
		if (!ticket.isValid()) {
			log.warn("Ticket with uuid {} is not valid", ticketUUID);
			return false;
		}

		var visitor = visitorProjectionPort.loadVisitor(new Ticket.TicketUUID(ticketUUID))
		                                   .orElse(new Visitor(new Visitor.VisitorUUID(UUID.randomUUID()), null));

		if (visitor.hasEntered()) {
			log.warn("Visitor with uuid {} already entered", ticketUUID);
			return false;
		}

		visitor.enter();
		visitorUpdatePorts.forEach(port -> port.visitorEntered(visitor, ticketUUID, gateUUID));
		return true;
	}
}
