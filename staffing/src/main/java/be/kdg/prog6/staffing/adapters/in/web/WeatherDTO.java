package be.kdg.prog6.staffing.adapters.in.web;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WeatherDTO {
	private LocalDate date;
	private WeatherType weatherType;
	private TemperatureType temperatureType;

	enum WeatherType {
		RAIN,
		CLOUD,
		SUNNY,
		STORM
	}

	enum TemperatureType {
		HOT,
		COLD
	}

}
