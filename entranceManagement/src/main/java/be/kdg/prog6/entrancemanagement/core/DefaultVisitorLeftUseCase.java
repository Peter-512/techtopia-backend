package be.kdg.prog6.entrancemanagement.core;

import be.kdg.prog6.entrancemanagement.domain.Ticket;
import be.kdg.prog6.entrancemanagement.ports.in.VisitorLeftUseCase;
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
public class DefaultVisitorLeftUseCase implements VisitorLeftUseCase {
	private final VisitorProjectionPort visitorProjectionPort;
	private final List<VisitorUpdatePort> visitorUpdatePorts;

	@Override
	@Transactional
	public boolean visitorLeft(UUID ticketUUID, UUID gateUUID) {
		var optionalVisitor = visitorProjectionPort.loadVisitor(new Ticket.TicketUUID(ticketUUID));

		if (optionalVisitor.isEmpty()) {
			log.warn("Ticket with uuid {} not found", ticketUUID);
			return false;
		}

		var visitor = optionalVisitor.get();
		if (visitor.hasLeft()) {
			log.warn("Visitor with uuid {} already left", ticketUUID);
			return false;
		}

		visitor.leave();
		visitorUpdatePorts.forEach(port -> port.visitorLeft(ticketUUID, gateUUID));
		return true;
	}
}
