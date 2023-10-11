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

	@Override
	public void visitorEntered(Visitor visitor, UUID ticketUUID, UUID gateUUID) {
		log.info("Visitor entered");

		final var optionalVisitor = visitorRepository.findByTicket(ticketUUID);
		if (optionalVisitor.isEmpty()) {
			var visitorJPA = new VisitorJpaEntity(visitor.getVisitorUUID().uuid(), ticketUUID, visitor.getState());
			visitorRepository.save(visitorJPA);
			return;
		}

		final var visitorJpa = optionalVisitor.get();
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
		visitorRepository.save(visitorJpa);
	}
}
