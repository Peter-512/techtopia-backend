package be.kdg.prog6.infopoints.adapters.in.amqp;

import be.kdg.prog6.common.events.AttractionChangedEvent;
import be.kdg.prog6.common.events.Event;
import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.facades.TechTopiaEventHandler;
import be.kdg.prog6.infopoints.ports.in.WaitingTimeUpdatedUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
@Slf4j
@AllArgsConstructor
public class WaitingTimeUpdatedAMQPAdapter implements TechTopiaEventHandler<AttractionChangedEvent> {
	private final ObjectMapper objectMapper;
	private final WaitingTimeUpdatedUseCase waitingTimeUpdatedUseCase;
	private final Set<UUID> eventStore = new HashSet<>();

	@Override
	public boolean appliesTo(EventCatalog eventType) {
		return EventCatalog.ATTRACTION_CHANGED.equals(eventType);
	}

	@Override
	public Optional<TechTopiaEventHandler<AttractionChangedEvent>> receive(EventMessage eventMessage) {
		final UUID eventID = eventMessage.getEventHeader().getEventID();
		final boolean isDuplicate = eventStore.contains(eventID);

		if (isDuplicate) {
			return Optional.empty();
		}

		eventStore.add(eventID);
		return Optional.of(this);
	}

	@Override
	public Event map(String eventBody) {
		log.info("Handling event: {}", eventBody);
		try {
			return objectMapper.readValue(eventBody, AttractionChangedEvent.class);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void handle(Event eventBody, EventCatalog eventType) {
		log.info("EVENT +++++++++ Waiting time +++++++++ event: {}", eventBody);
		try {
			waitingTimeUpdatedUseCase.updateWaitingTime((AttractionChangedEvent) eventBody);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
