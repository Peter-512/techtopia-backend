package be.kdg.prog6.infopoints.ports.in;

import be.kdg.prog6.common.events.EventMessage;

public interface WaitingTimeChangedPort {
	void updateWaitingTime(EventMessage eventMessage);
}
