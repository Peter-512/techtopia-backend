package be.kdg.prog6.staffing.ports.out;

import be.kdg.prog6.common.events.VisitorGateInteraction;

public interface VisitorGateInteractionPort {
	void saveVisitorGateInteraction(VisitorGateInteraction visitorGateInteraction);
}
