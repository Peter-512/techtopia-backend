package be.kdg.prog6.attractions.adapters.out.amqp;

import be.kdg.prog6.attractions.adapters.config.amqp.RabbitMQModuleTopology;
import be.kdg.prog6.attractions.domain.Attraction;
import be.kdg.prog6.attractions.ports.out.AttractionUpdatePort;
import be.kdg.prog6.common.events.AttractionChangedEvent;
import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventHeader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class AttractionChangeAMQPPublisher implements AttractionUpdatePort {
	private final RabbitTemplate rabbitTemplate;
	private final ObjectMapper objectMapper;

	@Override
	public void updateAttraction(Attraction attraction) {
		log.info("Publishing event that attraction changed");

		var eventHeader = EventHeader.builder()
		                             .eventID(UUID.randomUUID())
		                             .eventCatalog(EventCatalog.ATTRACTION_CHANGED)
		                             .build();

		var eventBody = new AttractionChangedEvent(
				attraction.getAttractionUUID().uuid(),
				attraction.getCurrentVisitors(),
				attraction.getThroughput().name());

		try {
			rabbitTemplate.convertAndSend(RabbitMQModuleTopology.ATTRACTION_CHANGED_COMMANDS, "attraction.changed",
					objectMapper.writeValueAsString(eventBody));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
