package be.kdg.prog6.staffing.adapters.in.web;

import be.kdg.prog6.staffing.ports.in.AverageVisitDurationQuery;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class AnalyticsController {
	private final AverageVisitDurationQuery averageVisitDurationQuery;

	@GetMapping ("analytics/average-visit-duration/start/{start}/end/{end}")
	public double getAverageVisitDuration(@PathVariable @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end, @PathVariable @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start) {
		return averageVisitDurationQuery.getAverageVisitDuration(start, end);
	}
}
