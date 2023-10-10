package be.kdg.prog6.parkticketing.adapter.out.publisher;

import be.kdg.prog6.common.events.TicketBoughtEvent;
import be.kdg.prog6.parkticketing.domain.Ticket;
import be.kdg.prog6.parkticketing.ports.out.TicketSoldPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
@Slf4j
public class TicketBoughtPublisher implements TicketSoldPort {
	private final ApplicationEventPublisher publisher;

	@Override
	public void buyTicket(Ticket.TicketUUID ticketUUID, LocalDate validOn) {
		log.info("Publishing ticket bought event for ticket with uuid {} on {}", ticketUUID.uuid(), validOn);
		publisher.publishEvent(new TicketBoughtEvent(ticketUUID.uuid(), validOn));
	}
}
