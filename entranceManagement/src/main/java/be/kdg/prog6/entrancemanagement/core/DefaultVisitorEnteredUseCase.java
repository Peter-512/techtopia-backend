package be.kdg.prog6.entrancemanagement.core;

import be.kdg.prog6.entrancemanagement.adapters.out.db.VisitorMapper;
import be.kdg.prog6.entrancemanagement.domain.Ticket;
import be.kdg.prog6.entrancemanagement.ports.in.TransitionVisitorCommand;
import be.kdg.prog6.entrancemanagement.ports.in.VisitorEnteredUseCase;
import be.kdg.prog6.entrancemanagement.ports.out.TicketPort;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorGateTransitionCommand;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorPort;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorUpdatePort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class DefaultVisitorEnteredUseCase implements VisitorEnteredUseCase {
	private final TicketPort ticketPort;
	private final VisitorPort visitorPort;
	private final List<VisitorUpdatePort> visitorUpdatePorts;
	private final VisitorMapper mapper = VisitorMapper.INSTANCE;

	@Override
	@Transactional
	public boolean visitorEntered(TransitionVisitorCommand command) {
		var ticketUUID = new Ticket.TicketUUID(command.ticketUUID());
		var optionalTicket = ticketPort.loadTicket(ticketUUID);
		if (optionalTicket.isEmpty()) {
			log.warn("Ticket with uuid {} not found", command.ticketUUID());
			return false;
		}

		var ticket = optionalTicket.get();
		if (!ticket.isValid()) {
			log.warn("Ticket with uuid {} is not valid", command.ticketUUID());
			return false;
		}

		var visitor = visitorPort.loadVisitor(ticketUUID)
		                         .orElse(mapper.create(command.ticketUUID()));

		if (visitor.hasEntered()) {
			log.warn("Visitor with uuid {} already entered", ticketUUID);
			return false;
		}

		visitor.enter();
		visitorUpdatePorts.forEach(port -> port.visitorEntered(new VisitorGateTransitionCommand(visitor, command.ticketUUID(), command.gateUUID())));
		return true;
	}
}
