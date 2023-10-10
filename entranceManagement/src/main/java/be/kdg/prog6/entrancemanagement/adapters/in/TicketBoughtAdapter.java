package be.kdg.prog6.entrancemanagement.adapters.in;

import be.kdg.prog6.common.events.TicketBoughtEvent;
import be.kdg.prog6.entrancemanagement.domain.Ticket;
import be.kdg.prog6.entrancemanagement.ports.in.SaveTicketCommand;
import be.kdg.prog6.entrancemanagement.ports.in.TicketBoughtPort;
import be.kdg.prog6.entrancemanagement.ports.in.TicketBoughtUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class TicketBoughtAdapter implements TicketBoughtPort {
	private final TicketBoughtUseCase ticketBoughtUseCase;


	@Override
	@EventListener
	public void buyTicket(TicketBoughtEvent event) {
		log.info("Ticket bought event received: {}", event);
		ticketBoughtUseCase.saveTicket(new SaveTicketCommand(new Ticket.TicketUUID(event.ticketUUID()), event.validOn()));
	}
}
