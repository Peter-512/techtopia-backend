package be.kdg.prog6.infopoints.core;

import be.kdg.prog6.common.events.AttractionChangedEvent;
import be.kdg.prog6.infopoints.domain.Attraction;
import be.kdg.prog6.infopoints.ports.in.WaitingTimeUpdatedUseCase;
import be.kdg.prog6.infopoints.ports.out.LoadPort;
import be.kdg.prog6.infopoints.ports.out.UpdatePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultWaitingTimeUpdatedUseCase implements WaitingTimeUpdatedUseCase {
	private final UpdatePort<Attraction> savePort;
	private final LoadPort<Attraction> loadPort;

	@Override
	public void updateWaitingTime(AttractionChangedEvent event) {
		log.info("Received event to update waiting time of attraction with uuid {}, waiting time {}", event.attractionUUID(), event.waitingTime());
		loadPort.load(event.attractionUUID()).ifPresent(attraction -> {
			attraction.setWaitingTime(event.waitingTime());
			savePort.update(attraction);
		});
	}
}
