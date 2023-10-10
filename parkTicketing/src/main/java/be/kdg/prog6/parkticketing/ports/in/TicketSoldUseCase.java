package be.kdg.prog6.parkticketing.ports.in;

import java.time.LocalDate;
import java.util.UUID;

public interface TicketSoldUseCase {
	void buyTicket(UUID uuid, LocalDate validOn);
}
