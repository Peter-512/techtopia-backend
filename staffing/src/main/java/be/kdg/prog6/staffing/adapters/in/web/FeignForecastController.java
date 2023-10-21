package be.kdg.prog6.staffing.adapters.in.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@FeignClient (value = "weather-service", url = "http://localhost:9090/")
public interface FeignForecastController {
	@GetMapping (value = "/weather/date/{date}")
	ResponseEntity<WeatherDTO> getWeather(@PathVariable @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate date);

	@GetMapping (value = "/forecast/date/{date}")
	ResponseEntity<VisitorForecastDTO> getForecast(@PathVariable @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate date);


}
