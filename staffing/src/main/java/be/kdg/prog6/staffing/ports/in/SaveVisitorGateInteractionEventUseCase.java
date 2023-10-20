package be.kdg.prog6.staffing.ports.in;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.VisitorGateInteraction;

public interface SaveVisitorGateInteractionEventUseCase {
	void registerGateInteraction(VisitorGateInteraction event, EventCatalog eventType);
}
