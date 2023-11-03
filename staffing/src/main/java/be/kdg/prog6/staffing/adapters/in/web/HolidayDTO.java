package be.kdg.prog6.staffing.adapters.in.web;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class HolidayDTO {
	UUID id;
	LocalDate startDate;
	LocalDate endDate;
	Name name;
	boolean nationwide;
	String type;

	@Data
	public class Name {
		String language;
		String text;
	}
}
