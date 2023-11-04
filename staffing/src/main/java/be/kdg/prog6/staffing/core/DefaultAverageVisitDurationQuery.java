package be.kdg.prog6.staffing.core;

import be.kdg.prog6.staffing.ports.in.AverageVisitDurationQuery;
import be.kdg.prog6.staffing.ports.out.VisitorGateInteractionPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultAverageVisitDurationQuery implements AverageVisitDurationQuery {
	private final VisitorGateInteractionPort port;

	@Override
	public double getAverageVisitDuration(UUID gateUUID, LocalDateTime start, LocalDateTime end) {
		return port.getGateBetween(gateUUID, start, end).calculateAverageTimeInPark();
	}
}
