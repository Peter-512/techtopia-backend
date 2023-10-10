package be.kdg.prog6.parkticketing.core;

import be.kdg.prog6.parkticketing.domain.Ticket;
import be.kdg.prog6.parkticketing.ports.in.TicketSoldUseCase;
import be.kdg.prog6.parkticketing.ports.out.TicketSoldPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultTicketSoldUseCase implements TicketSoldUseCase {
	private final List<TicketSoldPort> ports;

	@Override
	public void buyTicket(UUID uuid, LocalDate validOn) {
		Ticket.TicketUUID ticketUUID = new Ticket.TicketUUID(uuid);
		ports.forEach(port -> port.buyTicket(ticketUUID, validOn));
	}
}
