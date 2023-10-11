package be.kdg.prog6.entrancemanagement.adapters.out.db;

import be.kdg.prog6.entrancemanagement.domain.Ticket;
import be.kdg.prog6.entrancemanagement.domain.Visitor;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorProjectionPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class VisitorProjectionDBAdapter implements VisitorProjectionPort {
	private final VisitorRepository visitorRepository;

	@Override
	public Optional<Visitor> loadVisitor(Ticket.TicketUUID ticketUUID) {
		var optionalVisitorJpa = visitorRepository.findByTicket(ticketUUID.uuid());
		if (optionalVisitorJpa.isEmpty()) {
			return Optional.empty();
		}

		var visitorJPA = optionalVisitorJpa.get();
		var visitor = new Visitor(new Visitor.VisitorUUID(visitorJPA.getVisitor()), visitorJPA.getState());
		return Optional.of(visitor);
	}

	@Override
	public void saveVisitor(Visitor visitor) {
		//		var visitorEntity = new VisitorJpaEntity(visitor.getVisitorUUID().uuid(), visitor.getState());
		//		visitorRepository.save(visitorEntity);
	}
}
