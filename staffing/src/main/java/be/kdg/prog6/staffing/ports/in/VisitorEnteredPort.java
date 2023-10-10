package be.kdg.prog6.staffing.ports.in;

import be.kdg.prog6.common.events.VisitorEnteredEvent;

public interface VisitorEnteredPort {
	void visitorEntered(VisitorEnteredEvent event);
}
