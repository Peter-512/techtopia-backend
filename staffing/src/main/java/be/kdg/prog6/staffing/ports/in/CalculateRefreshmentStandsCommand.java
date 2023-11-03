package be.kdg.prog6.staffing.ports.in;

import be.kdg.prog6.staffing.domain.TemperatureType;
import be.kdg.prog6.staffing.domain.WeatherType;

import java.time.LocalDate;

public record CalculateRefreshmentStandsCommand(LocalDate date,
                                                boolean isHoliday,
                                                WeatherType weatherType,
                                                TemperatureType temperatureType,
                                                int predictedVisitors) {
}
