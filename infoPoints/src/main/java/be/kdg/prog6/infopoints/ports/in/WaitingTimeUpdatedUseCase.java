package be.kdg.prog6.infopoints.ports.in;

import be.kdg.prog6.common.events.AttractionChangedEvent;
import jakarta.transaction.Transactional;

public interface WaitingTimeUpdatedUseCase {
	@Transactional
	void updateWaitingTime(AttractionChangedEvent event);
}
