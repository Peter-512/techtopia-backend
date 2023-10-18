package be.kdg.prog6.entrancemanagement.adapters.out.db;

import be.kdg.prog6.entrancemanagement.domain.Ticket;
import be.kdg.prog6.entrancemanagement.domain.Visitor;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class VisitorDBAdapter implements VisitorPort {
	private final VisitorRepository visitorRepository;
	private final VisitorMapper mapper = VisitorMapper.INSTANCE;

	@Override
	public Optional<Visitor> loadVisitor(Ticket.TicketUUID ticketUUID) {
		var optionalVisitorJpa = visitorRepository.findByTicket(ticketUUID.uuid());
		return optionalVisitorJpa.map(mapper::map);
	}

	@Override
	public void saveVisitor(Visitor visitor) {
		var visitorEntity = mapper.map(visitor);
		visitorRepository.save(visitorEntity);
	}
}
