package be.kdg.prog6.parkticketing.adapters.out.amqp;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventHeader;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.events.TicketBoughtEvent;
import be.kdg.prog6.parkticketing.adapters.config.RabbitMQModuleTopology;
import be.kdg.prog6.parkticketing.domain.Ticket;
import be.kdg.prog6.parkticketing.ports.out.TicketSoldPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class TicketBoughtAMQPPublisher implements TicketSoldPort {
	private final RabbitTemplate rabbitTemplate;
	private final ObjectMapper objectMapper;

	@Override
	public void buyTicket(Ticket.TicketUUID ticketUUID, LocalDate validOn) {
		log.info("Publishing ticket bought event for ticket with uuid {} on {}", ticketUUID.uuid(), validOn);

		var eventHeader = EventHeader.builder()
		                             .eventID(UUID.randomUUID())
		                             .eventCatalog(EventCatalog.TICKET_BOUGHT)
		                             .build();
		var eventBody = new TicketBoughtEvent(ticketUUID.uuid(), validOn);

		try {
			rabbitTemplate.convertAndSend(RabbitMQModuleTopology.SAVE_TICKET_COMMANDS, "ticket.bought",
					EventMessage.builder()
					            .eventHeader(eventHeader)
					            .eventBody(objectMapper.writeValueAsString(eventBody))
					            .build());
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
