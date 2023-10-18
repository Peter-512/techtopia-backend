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
	private final VisitorMapper mapper = VisitorMapper.INSTANCE;

	@Override
	public void visitorEntered(Visitor visitor, UUID ticketUUID, UUID gateUUID) {
		log.info("Visitor entered");
		visitorRepository.save(mapper.map(visitor));
	}

	@Override
	public void visitorLeft(Visitor visitor, UUID ticketUUID, UUID gateUUID) {
		log.info("Visitor left");
		visitorRepository.save(mapper.map(visitor));
	}
}
