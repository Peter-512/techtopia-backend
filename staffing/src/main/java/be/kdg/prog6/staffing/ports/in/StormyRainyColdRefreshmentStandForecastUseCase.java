package be.kdg.prog6.staffing.ports.in;

import be.kdg.prog6.staffing.domain.TemperatureType;
import be.kdg.prog6.staffing.domain.WeatherType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StormyRainyColdRefreshmentStandForecastUseCase implements RefreshmentStandForecastUseCase {
	@Override
	public int getForecast(CalculateRefreshmentStandsCommand command) {
		var predictedAmount = command.predictedVisitors() * (command.isHoliday() ? 2 : 1);
		return predictedAmount / 1000;
	}

	@Override
	public boolean canHandle(CalculateRefreshmentStandsCommand command) {
		return command.temperatureType() == TemperatureType.COLD || command.weatherType() == WeatherType.RAIN || command.weatherType() == WeatherType.STORM;
	}
}
