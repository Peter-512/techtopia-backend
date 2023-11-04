package be.kdg.prog6.staffing.ports.in;

import java.time.LocalDateTime;

public interface AverageVisitDurationQuery {
	double getAverageVisitDuration(LocalDateTime start, LocalDateTime end);
}
