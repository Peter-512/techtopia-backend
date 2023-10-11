package be.kdg.prog6.staffing.ports.in;

import be.kdg.prog6.common.events.VisitorGateInteraction;

public interface VisitorGateInteractionPort {
	void visitorWentThroughGate(VisitorGateInteraction event);
}
