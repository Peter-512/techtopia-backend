package be.kdg.prog6.staffing.core;

import be.kdg.prog6.staffing.ports.in.CalculateRefreshmentStandsCommand;
import be.kdg.prog6.staffing.ports.in.RefreshmentStandForecastUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class WeatherStrategy {
	private final List<RefreshmentStandForecastUseCase> useCases;

	public int getForecast(CalculateRefreshmentStandsCommand command) {
		return useCases.stream()
		               .filter(useCase -> useCase.canHandle(command))
		               .findFirst()
		               .map(useCase -> useCase.getForecast(command))
		               .orElse(0);
	}
}
