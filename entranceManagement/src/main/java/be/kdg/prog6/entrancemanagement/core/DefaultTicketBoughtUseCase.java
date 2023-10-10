package be.kdg.prog6.entrancemanagement.core;

import be.kdg.prog6.entrancemanagement.domain.Ticket;
import be.kdg.prog6.entrancemanagement.ports.in.SaveTicketCommand;
import be.kdg.prog6.entrancemanagement.ports.in.TicketBoughtUseCase;
import be.kdg.prog6.entrancemanagement.ports.out.TicketProjectionPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultTicketBoughtUseCase implements TicketBoughtUseCase {
	private final TicketProjectionPort port;

	@Override
	@Transactional
	public void saveTicket(SaveTicketCommand command) {
		log.info("Received command to save ticket with uuid {}, valid on {}", command.ticketUUID(), command.validOn());
		port.saveTicket(Ticket.create(command.ticketUUID(), command.validOn()));
	}
}
