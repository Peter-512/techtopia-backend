package be.kdg.prog6.parkticketing.ports.in;

import be.kdg.prog6.common.events.TicketBoughtEvent;

public interface TicketSoldUseCase {
	void saveTicketSale(TicketBoughtEvent event);
}
