package be.kdg.prog6.entrancemanagement.adapters.out.db;

import be.kdg.prog6.entrancemanagement.domain.Ticket;
import be.kdg.prog6.entrancemanagement.ports.out.TicketPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
@Slf4j
public class TicketDBAdapter implements TicketPort {
	private final TicketRepository ticketRepository;
	private final TicketMapper mapper = TicketMapper.INSTANCE;

	@Override
	public Optional<Ticket> loadTicket(Ticket.TicketUUID ticketUUID) {
		var ticketEntity = ticketRepository.findByTicket(ticketUUID.uuid());
		return ticketEntity.map(mapper::map);
	}

	@Override
	public void saveTicket(Ticket ticket) {
		log.info("Saving ticket {}", ticket);
		ticketRepository.save(mapper.map(ticket));
	}
}
