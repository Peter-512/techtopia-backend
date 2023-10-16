package be.kdg.prog6.entrancemanagement.adapters.in.amqp;

import be.kdg.prog6.common.events.Event;
import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.TicketBoughtEvent;
import be.kdg.prog6.common.facades.TechTopiaEventHandler;
import be.kdg.prog6.entrancemanagement.ports.in.TicketBoughtUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class TicketBoughtAMQPAdapter implements TechTopiaEventHandler<TicketBoughtEvent> {
	private final ObjectMapper objectMapper;
	private final TicketBoughtUseCase ticketBoughtUseCase;

	@Override
	public boolean appliesTo(EventCatalog eventType) {
		return EventCatalog.TICKET_BOUGHT.equals(eventType);
	}

	@Override
	public Event map(String eventBody) {
		log.info("Handling event: {}", eventBody);
		try {
			return objectMapper.readValue(eventBody, TicketBoughtEvent.class);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void handle(Event eventBody, EventCatalog eventType) {
		log.info("EVENT +++++++++ TICKET BOUGHT +++++++++ event: {}", eventBody);
		ticketBoughtUseCase.saveTicket((TicketBoughtEvent) eventBody);
	}
}
