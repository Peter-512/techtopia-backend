package be.kdg.prog6.staffing.adapters.out;

import be.kdg.prog6.common.events.VisitorGateInteraction;
import be.kdg.prog6.staffing.ports.out.VisitorGateInteractionPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
@Slf4j
public class VisitorGateInteractionDBAdapter implements VisitorGateInteractionPort {
	private final VisitorGateInteractionRepository visitorGateInteractionRepository;
	private final VisitorGateInteractionMapper mapper = VisitorGateInteractionMapper.INSTANCE;

	@Override
	public void saveVisitorGateInteraction(VisitorGateInteraction visitorGateInteraction) {
		log.info("Saving visitor gate interaction: {}", visitorGateInteraction);
		visitorGateInteractionRepository.save(mapper.map(visitorGateInteraction));
	}
}
