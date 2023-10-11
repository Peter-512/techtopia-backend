package be.kdg.prog6.entrancemanagement.adapters.out.db;

import be.kdg.prog6.entrancemanagement.domain.Ticket;
import be.kdg.prog6.entrancemanagement.ports.out.TicketProjectionPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
@Slf4j
public class TicketProjectionDBAdapter implements TicketProjectionPort {
	private final TicketRepository ticketRepository;

	@Override
	public Optional<Ticket> loadTicket(Ticket.TicketUUID ticketUUID) {
		var ticketEntity = ticketRepository.findByTicket(ticketUUID.uuid());
		return ticketEntity.map(TicketJpaEntity::toDomain);
	}

	@Override
	public void saveTicket(Ticket ticket) {
		log.info("Saving ticket {}", ticket);
		ticketRepository.save(new TicketJpaEntity(ticket));
	}
}
