package be.kdg.prog6.staffing.adapters.in.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
@Slf4j
public class ForecastController {
	private final FeignForecastController feignForecastController;

	@Cacheable (value = "weather", key = "#date")
	@GetMapping (value = "/weather/{date}")
	public ResponseEntity<WeatherDTO> getWeather(@PathVariable @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return feignForecastController.getWeather(date);
	}

	@CacheEvict (value = "weather", allEntries = true)
	@Scheduled (fixedRateString = "86400000") // evict cache after 24 hours
	@DeleteMapping (value = "/cache/weather")
	public void evictWeatherCache() {
		log.info("Evicting weather cache");
	}

	@Cacheable (value = "forecast", key = "#date")
	@Retryable (maxAttempts = 5, backoff = @Backoff (delay = 500, multiplier = 2))
	@GetMapping (value = "/forecast/{date}")
	public ResponseEntity<VisitorForecastDTO> getForecast(@PathVariable @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		final ResponseEntity<VisitorForecastDTO> forecast = feignForecastController.getForecast(date);
		final VisitorForecastDTO body = forecast.getBody();

		if (body == null) {
			log.warn("Failed to get forecast: body is null");
			throw new RuntimeException("Failed to get forecast");
		}

		if (body.isError()) {
			log.warn("Failed to get forecast: {}", body.getErrorMessage());
			throw new RuntimeException("Failed to get forecast");
		}

		return forecast;
	}

	@CacheEvict (value = "forecast", allEntries = true)
	@Scheduled (fixedRateString = "86400000") // evict cache after 24 hours
	@DeleteMapping (value = "/cache/forecast")
	public void evictForecastCache() {
		log.info("Evicting forecast cache");
	}
}
