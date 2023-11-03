package be.kdg.prog6.staffing.adapters.in.web;

import be.kdg.prog6.staffing.core.WeatherStrategy;
import be.kdg.prog6.staffing.ports.in.CalculateRefreshmentStandsCommand;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
public class RefreshmentStandController {
	private final ForecastController forecastController;
	private final WeatherStrategy weatherStrategy;

	@Cacheable (value = "refreshment-stands", key = "#date")
	@GetMapping (value = "/refreshment-stands/{date}")
	public ResponseEntity<Integer> getRefreshmentStand(@PathVariable @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		var weatherBody = forecastController.getWeather(date).getBody();
		if (weatherBody == null) return ResponseEntity.badRequest().build();
		var weatherType = weatherBody.getWeatherType();
		var temperatureType = weatherBody.getTemperatureType();


		var visitorForecastBody = forecastController.getForecast(date).getBody();
		if (visitorForecastBody == null) return ResponseEntity.badRequest().build();
		var predictedVisitors = visitorForecastBody.getPredictedVisitors();

		var holidayBody = forecastController.getHoliday(date).getBody();
		if (holidayBody == null) return ResponseEntity.badRequest().build();
		var isHoliday = !holidayBody.getType().equals("None");
		var refreshmentStands = weatherStrategy.getForecast(new CalculateRefreshmentStandsCommand(date, isHoliday, weatherType, temperatureType, predictedVisitors));
		return ResponseEntity.ok(refreshmentStands);
	}
}
