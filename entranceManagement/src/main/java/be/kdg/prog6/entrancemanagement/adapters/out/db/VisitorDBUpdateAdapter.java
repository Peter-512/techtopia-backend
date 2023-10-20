package be.kdg.prog6.entrancemanagement.adapters.out.db;

import be.kdg.prog6.entrancemanagement.ports.out.VisitorGateTransitionCommand;
import be.kdg.prog6.entrancemanagement.ports.out.VisitorUpdatePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@AllArgsConstructor
public class VisitorDBUpdateAdapter implements VisitorUpdatePort {
	private final VisitorRepository visitorRepository;
	private final VisitorMapper mapper = VisitorMapper.INSTANCE;

	@Override
	public void visitorEntered(VisitorGateTransitionCommand command) {
		log.info("Visitor entered");
		visitorRepository.save(mapper.map(command.visitor()));
	}

	@Override
	public void visitorLeft(VisitorGateTransitionCommand command) {
		log.info("Visitor left");
		visitorRepository.save(mapper.map(command.visitor()));
	}
}
