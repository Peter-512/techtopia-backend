package be.kdg.prog6.entrancemanagement.ports.in;

import be.kdg.prog6.common.events.EventMessage;

public interface TicketBoughtPort {
	void buyTicket(EventMessage eventMessage);
}
