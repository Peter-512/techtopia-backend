package be.kdg.prog6.entrancemanagement.core;

import be.kdg.prog6.common.events.TicketBoughtEvent;
import be.kdg.prog6.entrancemanagement.domain.Ticket;
import be.kdg.prog6.entrancemanagement.ports.in.TicketBoughtUseCase;
import be.kdg.prog6.entrancemanagement.ports.out.TicketProjectionPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultTicketBoughtUseCase implements TicketBoughtUseCase {
	private final TicketProjectionPort port;

	@Override
	public void saveTicket(TicketBoughtEvent event) {
		log.info("Received event to save ticket with uuid {}, valid on {}", event.ticketUUID(), event.validOn());
		port.saveTicket(Ticket.create(new Ticket.TicketUUID(event.ticketUUID()), event.validOn()));
	}
}
