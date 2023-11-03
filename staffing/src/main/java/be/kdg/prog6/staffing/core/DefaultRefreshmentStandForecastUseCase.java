package be.kdg.prog6.staffing.core;

import be.kdg.prog6.staffing.domain.TemperatureType;
import be.kdg.prog6.staffing.domain.WeatherType;
import be.kdg.prog6.staffing.ports.in.CalculateRefreshmentStandsCommand;
import be.kdg.prog6.staffing.ports.in.RefreshmentStandForecastUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultRefreshmentStandForecastUseCase implements RefreshmentStandForecastUseCase {
	@Override
	public int getForecast(CalculateRefreshmentStandsCommand command) {
		var predicatedAmount = command.predictedVisitors() * (command.isHoliday() ? 2 : 1);
		return predicatedAmount / 500;
	}

	@Override
	public boolean canHandle(CalculateRefreshmentStandsCommand command) {
		return !(command.temperatureType() == TemperatureType.COLD || command.weatherType() == WeatherType.RAIN || command.weatherType() == WeatherType.STORM);
	}
}
