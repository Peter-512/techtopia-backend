package be.kdg.prog6.staffing.adapters.out;

import be.kdg.prog6.common.events.VisitorGateInteraction;
import be.kdg.prog6.staffing.domain.ActivityWindow;
import be.kdg.prog6.staffing.ports.out.VisitorGateInteractionPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
@AllArgsConstructor
@Slf4j
public class VisitorGateInteractionDBAdapter implements VisitorGateInteractionPort {
	private final VisitorGateInteractionRepository visitorGateInteractionRepository;
	private final VisitorGateInteractionMapper mapper = VisitorGateInteractionMapper.INSTANCE;
	private final GateInteractionActivityMapper activityMapper = GateInteractionActivityMapper.INSTANCE;

	@Override
	public void saveVisitorGateInteraction(VisitorGateInteraction visitorGateInteraction) {
		log.info("Saving visitor gate interaction: {}", visitorGateInteraction);
		visitorGateInteractionRepository.save(mapper.map(visitorGateInteraction));
	}

	@Override
	public ActivityWindow getGateBetween(UUID uuid, LocalDateTime start, LocalDateTime end) {
		if (end.isBefore(start))
			return new ActivityWindow();

		ActivityWindow activityWindow = new ActivityWindow();
		visitorGateInteractionRepository.findByGateUUIDAndPitBetween(uuid, start, end)
		                                .stream()
		                                .map(activityMapper::map)
		                                .forEach(activityWindow::add);
		return activityWindow;
	}

	@Override
	public ActivityWindow getGateAfter(UUID uuid, LocalDateTime start) {
		ActivityWindow activityWindow = new ActivityWindow();

		visitorGateInteractionRepository.findByGateUUIDAndPitAfter(uuid, start)
		                                .stream()
		                                .map(activityMapper::map)
		                                .forEach(activityWindow::add);
		return activityWindow;
	}
}
