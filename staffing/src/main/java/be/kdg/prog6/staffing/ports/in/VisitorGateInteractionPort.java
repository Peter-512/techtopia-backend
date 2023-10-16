package be.kdg.prog6.staffing.ports.in;

import be.kdg.prog6.common.events.EventMessage;

public interface VisitorGateInteractionPort {
	void visitorWentThroughGate(EventMessage eventMessage);
}
