package be.kdg.prog6.entrancemanagement.ports.in;

import be.kdg.prog6.common.events.TicketBoughtEvent;
import jakarta.transaction.Transactional;

public interface TicketBoughtUseCase {

	@Transactional
	void saveTicket(TicketBoughtEvent event);
}
