package be.kdg.prog6.entrancemanagement.ports.in;

import be.kdg.prog6.common.events.TicketBoughtEvent;

public interface TicketBoughtPort {
	void buyTicket(TicketBoughtEvent event);
}
