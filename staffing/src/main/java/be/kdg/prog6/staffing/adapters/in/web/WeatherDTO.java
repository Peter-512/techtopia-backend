package be.kdg.prog6.staffing.adapters.in.web;

import be.kdg.prog6.staffing.domain.TemperatureType;
import be.kdg.prog6.staffing.domain.WeatherType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WeatherDTO {
	private LocalDate date;
	private WeatherType weatherType;
	private TemperatureType temperatureType;
}
