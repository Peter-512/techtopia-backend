package be.kdg.prog6.staffing.adapters.in.web;

import be.kdg.prog6.staffing.adapters.out.RefreshmentStandMapper;
import be.kdg.prog6.staffing.core.WeatherStrategy;
import be.kdg.prog6.staffing.ports.in.CalculateRefreshmentStandsCommand;
import be.kdg.prog6.staffing.ports.in.CreateRefreshmentStandCommand;
import be.kdg.prog6.staffing.ports.in.CreateRefreshmentStandUseCase;
import be.kdg.prog6.staffing.ports.in.FetchRefreshmentStandsUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class RefreshmentStandController {
	private final ForecastController forecastController;
	private final WeatherStrategy weatherStrategy;
	private final RefreshmentStandMapper refreshmentStandMapper = RefreshmentStandMapper.INSTANCE;
	private final CreateRefreshmentStandUseCase createRefreshmentStandUseCase;
	private final FetchRefreshmentStandsUseCase fetchRefreshmentStandsUseCase;

	@Cacheable (value = "refreshment-stands", key = "#date")
	@GetMapping (value = "/refreshment-stands/{date}")
	public ResponseEntity<Integer> getRefreshmentStandCountBasedOnPrediction(@PathVariable @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate date) {
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

	@PostMapping ("/refreshment-stands/open")
	public ResponseEntity<Void> openRefreshmentStands(@RequestBody RefreshmentStandDTOin refreshmentStandDTO) {
		createRefreshmentStandUseCase.createRefreshmentStand(new CreateRefreshmentStandCommand(refreshmentStandDTO.getName(), refreshmentStandDTO.getCategory(), refreshmentStandDTO.getX(), refreshmentStandDTO.getY()));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping ("/refreshment-stands")
	public ResponseEntity<List<RefreshmentStandDTO>> getRefreshmentStands() {
		var refreshmentStands = fetchRefreshmentStandsUseCase.fetchRefreshmentStands()
		                                                     .stream()
		                                                     .map(refreshmentStandMapper::map)
		                                                     .toList();
		log.info("{}", refreshmentStands);
		return ResponseEntity.ok(refreshmentStands);
	}
}
