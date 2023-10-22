package be.kdg.prog6.entrancemanagement.core;

import be.kdg.prog6.entrancemanagement.domain.Ticket;
import be.kdg.prog6.entrancemanagement.ports.in.VisitorLeavingUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class VisitorLeavingStrategy {
	private final List<VisitorLeavingUseCase> useCases;

	public VisitorLeavingUseCase getStrategy(UUID ticketUUID) {
		VisitorLeavingStrategy.VisitorLeavingStrategyType strategy = Ticket.LOST_TICKET.equals(ticketUUID) ?
				VisitorLeavingStrategy.VisitorLeavingStrategyType.LOST_TICKET : VisitorLeavingStrategy.VisitorLeavingStrategyType.DEFAULT;

		return useCases.stream()
		               .filter(useCase -> useCase.getClass().getSimpleName().startsWith(strategy.pascalCaseName))
		               .findFirst()
		               .orElseThrow();
	}

	public enum VisitorLeavingStrategyType {
		DEFAULT,
		LOST_TICKET;

		final String pascalCaseName = Arrays.stream(name().toLowerCase().split("_"))
		                                    .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
		                                    .reduce("", String::concat);
	}
}
