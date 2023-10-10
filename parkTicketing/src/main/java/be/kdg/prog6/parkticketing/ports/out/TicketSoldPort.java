package be.kdg.prog6.parkticketing.ports.out;

import be.kdg.prog6.parkticketing.domain.Ticket;

import java.time.LocalDate;

public interface TicketSoldPort {
	void buyTicket(Ticket.TicketUUID ticketUUID, LocalDate validOn);
}
