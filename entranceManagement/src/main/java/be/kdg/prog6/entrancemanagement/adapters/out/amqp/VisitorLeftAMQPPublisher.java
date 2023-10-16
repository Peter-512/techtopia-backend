package be.kdg.prog6.entrancemanagement.adapters.out.amqp;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventHeader;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.events.VisitorGateInteraction;
import be.kdg.prog6.entrancemanagement.adapters.config.RabbitMQModuleTopology;
import be.kdg.prog6.entrancemanagement.domain.Visitor;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorUpdatePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class VisitorLeftAMQPPublisher implements VisitorUpdatePort {
	private final RabbitTemplate rabbitTemplate;

	private final ObjectMapper objectMapper;

	@Override
	public void visitorEntered(Visitor visitor, UUID ticketUUID, UUID gateUUID) {
		log.info("Publishing event that visitor left");

		var eventHeader = EventHeader.builder()
		                             .eventID(UUID.randomUUID())
		                             .eventCatalog(EventCatalog.VISITOR_ENTERED)
		                             .build();
		var eventBody = new VisitorGateInteraction(ticketUUID, gateUUID, LocalDateTime.now(), visitor.getState()
		                                                                                             .name());
		try {
			rabbitTemplate.convertAndSend(RabbitMQModuleTopology.TECHTOPIA_EVENTS_FAN_OUT, "entrance.visitor.entered",
					EventMessage.builder()
					            .eventHeader(eventHeader)
					            .eventBody(objectMapper.writeValueAsString(eventBody))
					            .build());
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void visitorLeft(Visitor visitor, UUID ticketUUID, UUID gateUUID) {
		log.info("Publishing event that visitor left");

		var eventHeader = EventHeader.builder()
		                             .eventID(UUID.randomUUID())
		                             .eventCatalog(EventCatalog.VISITOR_LEFT)
		                             .build();
		var eventBody = new VisitorGateInteraction(ticketUUID, gateUUID, LocalDateTime.now(), visitor.getState()
		                                                                                             .name());
		try {
			rabbitTemplate.convertAndSend(RabbitMQModuleTopology.TECHTOPIA_EVENTS_FAN_OUT, "entrance.visitor.left",
					EventMessage.builder()
					            .eventHeader(eventHeader)
					            .eventBody(objectMapper.writeValueAsString(eventBody))
					            .build());
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
