package be.kdg.prog6.attractions.adapters.out.amqp;

import be.kdg.prog6.attractions.adapters.config.amqp.RabbitMQModuleTopology;
import be.kdg.prog6.attractions.domain.Attraction;
import be.kdg.prog6.attractions.ports.out.AttractionUpdatePort;
import be.kdg.prog6.common.events.AttractionChangedEvent;
import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventHeader;
import be.kdg.prog6.common.events.EventMessage;
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
public class AttractionChangeAMQPPublisher implements AttractionUpdatePort {
	private final RabbitTemplate rabbitTemplate;
	private final ObjectMapper objectMapper;

	@Override
	public void updateAttraction(Attraction attraction) {
		if (!attraction.hasWaitingTimeChanged()) {
			log.info("Not publishing {}", attraction);
			return;
		}

		log.info("Publishing event that attraction changed");
		log.info("{}", attraction);

		var eventHeader = EventHeader.builder()
		                             .eventID(UUID.randomUUID())
		                             .eventCatalog(EventCatalog.ATTRACTION_CHANGED)
		                             .build();

		var eventBody = new AttractionChangedEvent(
				attraction.getAttractionUUID().uuid(),
				LocalDateTime.now(),
				attraction.getCurrentWaitingTime());

		try {
			rabbitTemplate.convertAndSend(RabbitMQModuleTopology.ATTRACTION_CHANGED_COMMANDS, "attraction.changed",
					EventMessage.builder()
					            .eventHeader(eventHeader)
					            .eventBody(objectMapper.writeValueAsString(eventBody))
					            .build());
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
