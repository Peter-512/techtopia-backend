package be.kdg.prog6.entrancemanagement.ports.in;

import jakarta.transaction.Transactional;

public interface TicketBoughtUseCase {

	@Transactional
	void saveTicket(SaveTicketCommand command);
}
