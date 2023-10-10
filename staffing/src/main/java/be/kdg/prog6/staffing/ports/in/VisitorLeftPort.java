package be.kdg.prog6.staffing.ports.in;

import be.kdg.prog6.common.events.VisitorLeftEvent;

public interface VisitorLeftPort {
	void visitorLeft(VisitorLeftEvent event);
}
