package be.kdg.prog6.staffing.ports.out;

import be.kdg.prog6.common.events.VisitorGateInteraction;
import be.kdg.prog6.staffing.domain.ActivityWindow;

import java.time.LocalDateTime;
import java.util.UUID;

public interface VisitorGateInteractionPort {
	void saveVisitorGateInteraction(VisitorGateInteraction visitorGateInteraction);

	ActivityWindow getGateBetween(LocalDateTime start, LocalDateTime end);

	ActivityWindow getGateAfter(UUID uuid, LocalDateTime start);
}
