package be.kdg.prog6.staffing.adapters.in.web;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VisitorForecastDTO {
	private LocalDate date;
	private int predictedVisitors;
	private boolean error;
	private String errorMessage;
}
