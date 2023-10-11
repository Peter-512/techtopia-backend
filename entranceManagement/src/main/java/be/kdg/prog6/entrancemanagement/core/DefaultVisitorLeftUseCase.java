package be.kdg.prog6.entrancemanagement.core;

import be.kdg.prog6.entrancemanagement.domain.Ticket;
import be.kdg.prog6.entrancemanagement.ports.in.VisitorLeftUseCase;
import be.kdg.prog6.entrancemanagement.ports.out.TicketProjectionPort;
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
	private final TicketProjectionPort ticketProjectionPort;
	private final List<VisitorUpdatePort> visitorUpdatePorts;

	@Override
	@Transactional
	public void visitorLeft(UUID ticketUUID, UUID gateUUID) {
		var optionalTicket = ticketProjectionPort.loadTicket(new Ticket.TicketUUID(ticketUUID));

		if (optionalTicket.isEmpty()) {
			log.warn("Ticket with uuid {} not found", ticketUUID);
			return;
		}

		var ticket = optionalTicket.get();
		if (ticket.isValid()) {
			visitorUpdatePorts.forEach(port -> port.visitorLeft(ticketUUID, gateUUID));
		}
	}
}
