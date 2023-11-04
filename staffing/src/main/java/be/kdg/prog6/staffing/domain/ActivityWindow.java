package be.kdg.prog6.staffing.domain;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@ToString
public class ActivityWindow {
	private final List<GateInteractionActivity> activities = new ArrayList<>();

	public int calculateDifferenceInEntryAndExit() {
		return activities.stream()
		                 .map(activity -> activity.action() == GateInteractionAction.ENTERED ? 1 : -1)
		                 .reduce(0, Integer::sum);
	}

	public double calculateAverageTimeInPark() {
		Map<UUID, List<GateInteractionActivity>> activitiesByTicket = activities.stream()
		                                                                        .collect(Collectors.groupingBy(GateInteractionActivity::ticketUUID));
		return activitiesByTicket.values().stream()
		                         .map(activities -> activities.stream()
		                                                      .sorted(Comparator.comparing(GateInteractionActivity::pit))
		                                                      .collect(Collectors.toList()))
		                         .mapToLong(this::calculateVisitorDuration)
		                         .average()
		                         .orElse(0.0);
	}

	private long calculateVisitorDuration(List<GateInteractionActivity> activities) {
		LocalDateTime enterTime = null;
		long totalDuration = 0;
		for (GateInteractionActivity activity : activities) {
			if (activity.action() == GateInteractionAction.ENTERED) {
				enterTime = activity.pit();
			} else if (activity.action() == GateInteractionAction.LEFT && enterTime != null) {
				totalDuration += Duration.between(enterTime, activity.pit()).toMinutes();
				enterTime = null;
			}
		}

		return totalDuration;
	}

	public LocalDateTime getStartTimestamp() {
		return activities.stream()
		                 .min(Comparator.comparing(GateInteractionActivity::pit))
		                 .orElseThrow(IllegalStateException::new).pit();
	}

	public LocalDateTime getEndTimestamp() {
		return activities.stream()
		                 .max(Comparator.comparing(GateInteractionActivity::pit))
		                 .orElseThrow(IllegalStateException::new)
		                 .pit();
	}

	public int size() {
		return activities.size();
	}

	public boolean isEmpty() {
		return activities.isEmpty();
	}

	public boolean add(GateInteractionActivity activity) {
		return activities.add(activity);
	}
}
