package be.kdg.prog6.entrancemanagement.adapters.out.db;

import be.kdg.prog6.entrancemanagement.domain.Visitor;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorUpdatePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Slf4j
@Repository
@AllArgsConstructor
public class VisitorDBUpdateAdapter implements VisitorUpdatePort {
	private final VisitorRepository visitorRepository;
	private final TicketRepository ticketRepository;

	@Override
	public void visitorEntered(UUID ticketUUID, UUID gateUUID) {
		log.info("Visitor entered");

		final var optionalVisitor = visitorRepository.findByTicket(ticketUUID);
		final var optionalTicketJpa = ticketRepository.findByTicket(ticketUUID);
		if (optionalVisitor.isEmpty()) {
			var ticket = optionalTicketJpa.map(TicketJpaEntity::toDomain);
			var visitor = ticket.map(v -> new Visitor(new Visitor.VisitorUUID(UUID.randomUUID()), Visitor.State.ENTERED));
			var visitorJPA = visitor.map(v ->
					new VisitorJpaEntity(v.getVisitorUUID().uuid(), ticketUUID, v.getState())
			);
			visitorJPA.ifPresent(visitorRepository::save);
			return;
		}

		final var visitorJpa = optionalVisitor.get();
		visitorJpa.enter();
		visitorRepository.save(visitorJpa);
	}

	@Override
	public void visitorLeft(UUID ticketUUID, UUID gateUUID) {
		log.info("Visitor left");
		var optionalVisitorJpa = visitorRepository.findByTicket(ticketUUID);
		if (optionalVisitorJpa.isEmpty()) {
			log.warn("Visitor with uuid {} not found", ticketUUID);
			return;
		}
		final var visitorJpa = optionalVisitorJpa.get();
		visitorJpa.leave();
		visitorRepository.save(visitorJpa);
	}
}
