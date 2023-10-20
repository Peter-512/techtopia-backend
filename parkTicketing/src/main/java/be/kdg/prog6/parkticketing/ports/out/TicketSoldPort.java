package be.kdg.prog6.parkticketing.ports.out;

import be.kdg.prog6.common.events.TicketBoughtEvent;

public interface TicketSoldPort {
	void buyTicket(TicketBoughtEvent event);
}
