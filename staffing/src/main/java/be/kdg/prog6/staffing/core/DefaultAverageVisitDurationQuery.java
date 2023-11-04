package be.kdg.prog6.staffing.core;

import be.kdg.prog6.staffing.ports.in.AverageVisitDurationQuery;
import be.kdg.prog6.staffing.ports.out.VisitorGateInteractionPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultAverageVisitDurationQuery implements AverageVisitDurationQuery {
	private final VisitorGateInteractionPort port;

	@Override
	public double getAverageVisitDuration(LocalDateTime start, LocalDateTime end) {
		return port.getGateBetween(start, end).calculateAverageTimeInPark();
	}
}
