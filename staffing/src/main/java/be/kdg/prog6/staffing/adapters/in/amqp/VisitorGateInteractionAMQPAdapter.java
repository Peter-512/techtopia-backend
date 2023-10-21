package be.kdg.prog6.staffing.adapters.in.amqp;

import be.kdg.prog6.common.events.Event;
import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.VisitorGateInteraction;
import be.kdg.prog6.common.facades.TechTopiaEventHandler;
import be.kdg.prog6.staffing.domain.Park;
import be.kdg.prog6.staffing.ports.in.SaveVisitorGateInteractionEventUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class VisitorGateInteractionAMQPAdapter implements TechTopiaEventHandler<VisitorGateInteraction> {
	private final ObjectMapper objectMapper;
	private final SaveVisitorGateInteractionEventUseCase saveVisitorGateInteractionEventUseCase;

	@Override
	public boolean appliesTo(EventCatalog eventType) {
		return EventCatalog.VISITOR_ENTERED.equals(eventType) || EventCatalog.VISITOR_LEFT.equals(eventType);
	}

	@Override
	public Event map(String eventBody) {
		log.info("Handling event: {}", eventBody);
		try {
			return objectMapper.readValue(eventBody, VisitorGateInteraction.class);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void handle(Event eventBody, EventCatalog eventType) {
		log.info("EVENT +++++++++ VISITOR WENT THROUGH GATE +++++++++ event: {}", eventBody);
		saveVisitorGateInteractionEventUseCase.registerGateInteraction((VisitorGateInteraction) eventBody, eventType);
		log.info("Park: {}", Park.instance());
	}
}
