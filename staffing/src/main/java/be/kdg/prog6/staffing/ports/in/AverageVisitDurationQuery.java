package be.kdg.prog6.staffing.ports.in;

import java.time.LocalDateTime;
import java.util.UUID;

public interface AverageVisitDurationQuery {
	double getAverageVisitDuration(UUID gateUUID, LocalDateTime start, LocalDateTime end);
}
