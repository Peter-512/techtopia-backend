package be.kdg.prog6.entrancemanagement.core;

import be.kdg.prog6.common.events.TicketBoughtEvent;
import be.kdg.prog6.entrancemanagement.adapters.out.db.TicketMapper;
import be.kdg.prog6.entrancemanagement.ports.in.TicketBoughtUseCase;
import be.kdg.prog6.entrancemanagement.ports.out.TicketPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultTicketBoughtUseCase implements TicketBoughtUseCase {
	private final TicketPort port;
	private final TicketMapper mapper = TicketMapper.INSTANCE;

	@Override
	public void saveTicket(TicketBoughtEvent event) {
		log.info("Received event to save ticket with uuid {}, valid on {}", event.ticketUUID(), event.validOn());
		port.saveTicket(mapper.create(event.ticketUUID(), event.validOn()));
	}
}
